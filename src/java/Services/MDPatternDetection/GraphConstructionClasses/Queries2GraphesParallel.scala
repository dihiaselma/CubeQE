package Services.MDPatternDetection.GraphConstructionClasses

import java.io.{File, FileOutputStream, PrintWriter}

import Services.MDfromLogQueries.Declarations.Declarations
import Services.MDfromLogQueries.Util.Constants2
import org.apache.jena.query.{Query, QueryFactory}

import scala.collection.parallel.ParSeq
import scala.io.Source

object Queries2GraphesParallel   {

  var queriesNumber=0


  //: util.ArrayList[Query]
  def TransformQueriesInFile(filePath: String) = {


    new Constants2()

    val lines = Source.fromFile(filePath).getLines

    lines.grouped(100000).foreach {

      groupOfLines => {

        var nb_req = 0

        val treatedGroupOfLines = groupOfLines.par.map {
          line => {
            nb_req = nb_req + 1
            println("* " + nb_req)

            var constructedQuery = QueryFactory.create()
            try {
              val query = QueryFactory.create(line)
              if (query.isConstructType)
                query.setQuerySelectType();
              val queryUpdate = new QueryUpdate(query)
              constructedQuery = queryUpdate.toConstruct(query)
              /* Some meaning if there is a result != null */
              Some(constructedQuery)
              //  Some(query)

            } catch {
              case unknown => {
                println("une erreur\n")
                writeInLogFile(Declarations.paths.get("constructLogFileParallel"), constructedQuery)
                None
              }
            }

          }

        }

        println("--------------------- un group finished ---------------------------------- ")

        val constructedQueries: ParSeq[Query]= treatedGroupOfLines.collect { case Some(x) => x }
        writeInFile(Declarations.paths.get("constructQueriesFile2"), constructedQueries )

        }
    }

  }

  /** Function that writes into destinationFilePath the list passed as parameter **/
  def writeInFile(destinationFilePath: String, queries: ParSeq[Query]) = {


    val writer = new PrintWriter(new FileOutputStream(new File(destinationFilePath), true))

    queries.foreach(query => {
      queriesNumber+=1
      writer.write(query.toString().replaceAll("[\n\r]", "\t") + "\n")
    })

    writer.close()
  }


  def writeInLogFile(destinationFilePath: String, query: Query) = {

    val writer = new PrintWriter(new FileOutputStream(new File(destinationFilePath), true))

    writer.write(query.toString().replaceAll("[\n\r]", "\t") + "\n")

    writer.close()
  }


}
