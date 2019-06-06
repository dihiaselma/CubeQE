package Services.MDfromLogQueries.LogCleaning

import java.io.{File, FileOutputStream, PrintWriter}
import java.util.regex.Pattern

import Services.MDfromLogQueries.Declarations.Declarations
import Services.MDfromLogQueries.LogCleaning.LogCleaning.queryFromLogLine

import scala.collection.parallel.ParSeq
import scala.io.Source

object LogCleaningOneFile {
  /** This class reads the log files and extract queries **/
  var queriesNumber = 0
  var nbLines = 0


  /* Regex on wich is based the algorithm to extract the queries */
  private val PATTERN = Pattern.compile("[^\"]*\"(?:GET )?/sparql/?\\?([^\"\\s\\n]*)[^\"]*\".*")
  //private val PATTERN = Pattern.compile("(sparql)(.*)")
  /* Statistical variables*/


  /** Write the cleaned queries in the destination file path **/
  def writeFiles(filePath: String, destinationfilePath: String) = {
    println(filePath)
    println(destinationfilePath)
    var dir = new File(filePath)


    var queryList = Source.fromFile(dir.listFiles().toIterator.next()).getLines

    queryList.grouped(10000).foreach {
      groupOfLines => {
        var nb_line = 0
        val treatedGroupOfLines = groupOfLines.par.map {
          line => {
            try {
              nb_line+=1
              val extractedQuery = queryFromLogLine(line, PATTERN)
              if (extractedQuery != null) {
                queriesNumber += 1
                println("* " + nb_line)
                Right(Some(extractedQuery))
              } else Left(line)

            } catch {
              case e: Exception => {
                e.printStackTrace()
                println("une erreur")
                Left(line)
              }
            }
          }
        }

        println("--------------------- un group finished ---------------------------------- ")
        nbLines = nbLines + nb_line
        val (correct, errors) = treatedGroupOfLines.partition(_.isRight)
        writeInFile(destinationfilePath, correct.collect { case Right(Some(x)) => x })
        writeInFile(Declarations.paths.get("notCleanedQueries"), errors.collect { case Left(line) => line })
      }
    }
    println(" Lines number inside the log  :" + nbLines)
    println(" Queries number inside the log  :" + queriesNumber)

    null
  }

  def writeInFile(destinationFilePath: String, queries: ParSeq[String]) = {


    val writer = new PrintWriter(new FileOutputStream(new File(destinationFilePath), true))


    queries.foreach(query => {
      writer.write(query.toString().replaceAll("[\n\r]", "\t") + "\n")
    })
    writer.close()
  }

}
