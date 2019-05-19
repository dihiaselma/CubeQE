package Services.MDfromLogQueries.LogCleaning

import Services.MDfromLogQueries.Declarations.Declarations
import Services.MDfromLogQueries.LogCleaning.logCleaningWikiData.{nb_queries, queryFromLogLineWD, writeInFile}

import scala.collection.mutable
import scala.io.Source

object QueriesDeduplicatorScala extends App {

  var queriesNumber = 0

  def DeduplicateQueriesInFile (filePath: String): Unit = {


    var queriesDeduplicatedSet : mutable.HashSet[String]=null

    val queryList = Source.fromFile(filePath).getLines
    var nb_group =0

    queryList.grouped(100000).foreach {

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
        nb_queries = nb_queries + nb_req


        val (correct, errors) = treatedGroupOfLines.partition(_.isRight)
        writeInFile(destinationfilePath, correct.collect { case Right(Some(x)) => x })
        writeInFile(Declarations.paths.get("notCleanedQueries")+nb_group+".txt", errors.collect { case Left(line) => line })
      }
    }

    println("nombre de requêtes dans le log :" + nb_queries)
    null

  }





  }
