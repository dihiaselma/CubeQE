package Services.MDfromLogQueries.LogCleaning

import java.io.{File, FileOutputStream, PrintWriter}

import scala.collection.mutable
import scala.io.Source

object QueriesDeduplicatorParallel extends App {

  var queriesNumber = 0

  def DeduplicateQueriesInFile2   (filePath: String, destinationfilePath: String): Unit = {


    //var queriesDeduplicatedSet : mutable.HashSet[String]=null

    val queryList = Source.fromFile(filePath).getLines
    var nb_group =0

    queryList.grouped(100000).foreach {
      val queriesDeduplicatedSet : mutable.HashSet[String] = new mutable.HashSet[String]()
      groupOfLines => {
        var nb_req = 0
        nb_group+=1
        val treatedGroupOfLines = groupOfLines.par.foreach {
          line => {


              queriesDeduplicatedSet.add(line)
              nb_req += 1

            println("* " + nb_req)
          }
        }

        println("--------------------- un group finished ---------------------------------- ")

        queriesNumber+=queriesDeduplicatedSet.size

        writeInFile(destinationfilePath, queriesDeduplicatedSet)

      }
    }

    println("nombre de requêtes dans le log :" +queriesNumber)


  }

  def writeInFile(destinationFilePath: String, queriesSet: mutable.HashSet[String]) = {


    val writer = new PrintWriter(new FileOutputStream(new File(destinationFilePath), true))

    queriesSet.foreach(query => writer.write(query.replaceAll("[\n\r]", "\t") + "\n"))

    writer.close()
  }




  }
