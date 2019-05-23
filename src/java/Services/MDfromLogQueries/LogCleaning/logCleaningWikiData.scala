package Services.MDfromLogQueries.LogCleaning

import java.io.{File, FileOutputStream, PrintWriter}
import java.net.URLDecoder
import java.nio.charset.StandardCharsets
import java.util.regex.Pattern

import Services.MDfromLogQueries.Declarations.Declarations

import scala.collection.parallel.ParSeq
import scala.io.Source

object logCleaningWikiData    {

  /** This class reads the log files and extract queries **/


  val t1 = System.currentTimeMillis()

  var queriesNumber=0
  var PATTERN_wikidata : Pattern = null
  /* Directory that coontains the log files 's Path */
  val directoryPath = Declarations.paths.get("directoryPath")


  /* Result (cleaned queries)'s file path */
  val destinationfilePath = Declarations.paths.get("cleanedQueriesFileCopie")
  val duration = System.currentTimeMillis() - t1
  val dir = new File(directoryPath)





  /* Statistical variables*/
  var nb_queries = 0

  /** Write the cleaned queries in the destination file path **/
  def writeFilesWikidata (filePath: String, destinationfilePath: String) = {

    PATTERN_wikidata=   Pattern.compile("([^\"\\s\\n]*)[^\"]*")
    val queryList = Source.fromFile(filePath).getLines
      var nb_group =0
    queryList.grouped(100000).foreach {
      groupOfLines => {
        var nb_req = 0
        nb_group+=1
        val treatedGroupOfLines = groupOfLines.par.map {
          line => {
            try {
//              println(line)

              val extractedQuery = queryFromLogLineWD(line)
             // println(queryFromRequest(extractedQuery))


              if (extractedQuery != null) {
                nb_req += 1
                println("* " + nb_req)
                Right(Some(extractedQuery))
              } else Left(line)

            } catch {
              case e: Exception => {
                e.printStackTrace()
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
        writeInFile(Declarations.paths.get("notCleanedQueries")+nb_group+".txt", errors.collect { case Left(line) => line })
      }
    }
    println("nombre de requêtes dans le log :" + nb_queries)
    null
  }

  def writeInFile(destinationFilePath: String, queries: ParSeq[String]) = {


    val writer = new PrintWriter(new FileOutputStream(new File(destinationFilePath), true))


    queries.foreach(query => {
      queriesNumber+=1
      writer.write(query.toString().replaceAll("[\n\r]", "\t") + "\n")
    })
    writer.close()
  }


  /** match the line passed as parameter with the Regex to extract the query and return the query **/
  def queryFromLogLineWD(line: String  ) = {


    val matcher= PATTERN_wikidata.matcher(line)

    if (matcher.find) {
      val requestStr = matcher.group(1)

      val queryStr = queryFromRequestWD(requestStr)

      if (queryStr != null) queryStr
      else requestStr
    }
    else null
  }


  def queryFromRequestWD(requestStr: String): String = {
     URLDecoder.decode(requestStr, StandardCharsets.UTF_8)
  }



 // dir.listFiles().toList.foreach(filePath => writeFilesWikidata(filePath.toString, destinationfilePath))

  println(duration)
}
