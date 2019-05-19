package Services.MDfromLogQueries.LogCleaning

import java.io.{File, FileOutputStream, PrintWriter}
import java.util.regex.Pattern

import Services.MDfromLogQueries.Declarations.Declarations
import Services.MDfromLogQueries.LogCleaning.LogCleaning.{queryFromLogLine, queryFromRequest}

import scala.collection.parallel.ParSeq
import scala.io.Source

object LogCleaningOneFile extends App {
  /** This class reads the log files and extract queries **/

  val t1 = System.currentTimeMillis()
  print("je suis dans log cleaning")

  /* Directory that coontains the log files 's Path */
  val directoryPath = Declarations.paths.get("directoryPath")

  /* Result (cleaned queries)'s file path */
  val destinationfilePath = Declarations.paths.get("cleanedQueriesFileCopie")
  val duration = System.currentTimeMillis() - t1
  val dir = new File(directoryPath)
  /* Regex on wich is based the algorithm to extract the queries */
  private val PATTERN = Pattern.compile("[^\"]*\"(?:GET )?/sparql/?\\?([^\"\\s\\n]*)[^\"]*\".*")




  /* Statistical variables*/
  var nb_queries = 0

  /** Write the cleaned queries in the destination file path **/
  def writeFiles2 (filePath: String, destinationfilePath: String) = {
    var queryList = Source.fromFile(filePath).getLines

    queryList.grouped(100000).foreach {
      groupOfLines => {
        var nb_req = 0
        val treatedGroupOfLines = groupOfLines.par.map {
          line => {
            try {
              println(line)

              val extractedQuery = queryFromLogLine(line)
              println(queryFromRequest(extractedQuery))


              if (extractedQuery != null) {
                nb_req += 1
                println("* " + nb_req)
                Right(Some(extractedQuery))
              } else Left(line)

            } catch {
              case e: Exception => {
                println("une erreur\n\n\n\n\n\n\n\n\n")
                Left(line)
              }
            }
          }
        }

        println("--------------------- un group finished ---------------------------------- ")
        nb_queries = nb_queries + nb_req
        val (correct, errors) = treatedGroupOfLines.partition(_.isRight)
        writeInFile(destinationfilePath, correct.collect { case Right(Some(x)) => x })
        writeInFile(Declarations.paths.get("notCleanedQueries"), errors.collect { case Left(line) => line })
      }
    }
    println("nombre de requêtes dans le log :" + nb_queries)
    null
  }

  def writeInFile(destinationFilePath: String, queries: ParSeq[String]) = {


    val writer = new PrintWriter(new FileOutputStream(new File(destinationFilePath), true))

    queries.foreach(query => writer.write(query.replaceAll("[\n\r]", "\t") + "\n"))

    writer.close()
  }



  println(duration)
}
