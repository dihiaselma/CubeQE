package Services.MDfromLogQueries.LogCleaning

import java.io.{File, PrintWriter}

import Services.MDfromLogQueries.LogCleaning.LogCleaning.queryFromLogLine

import scala.io.Source

object logCleaningHugeFiles  extends App {


  val t1 = System.currentTimeMillis()
  var queriesNumber = 0



  //: util.ArrayList[Query]
  def cleanFile (filePath: String, destinationfilePath:String) = {


    val lines =Source.fromFile(filePath).getLines

    lines.grouped(100000).foreach {

      groupOfLines => {
        var nb_req = 0
        val treatedGroupOfLines = groupOfLines.par.map {

          line => {
            nb_req = nb_req + 1

              queryFromLogLine(line)

          }
        }
        println("--------------------- un group finished ---------------------------------- ")

        val writer = new PrintWriter(new File(destinationfilePath))


        treatedGroupOfLines.foreach(query => if (query != null) {
          queriesNumber += 1
          writer.write(query.replaceAll("[\n\r]", "\t") + "\n")
        })
        writer.close()
      }
    }

  }





}
