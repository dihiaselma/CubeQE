package Services.MDPatternDetection.ExecutionClasses

import java.util.concurrent.TimeUnit

import Services.MDfromLogQueries.Declarations.Declarations
import Services.MDfromLogQueries.Util.TdbOperation
import QueryExecutorParallel.{writeInLogFile, writeInTdb}
import org.apache.jena.query.{Query, QueryExecutionFactory, QueryFactory}
import org.apache.jena.rdf.model.{Model, ModelFactory}
import org.apache.jena.sparql.engine.http.QueryEngineHTTP

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future, future}
import scala.io.Source



object QueryExecutorParallelFuture extends App {

  val t1 = System.currentTimeMillis()

  val tdb = new TdbOperation()



  executeQueriesInFile(Declarations.paths.get("constructQueriesFile2"), "http://scholarlydata.org/sparql/")
  val duration = System.currentTimeMillis() - t1
  var numQueryRun = 0

  def executeQueriesInFile(filePath: String, endPoint: String) = {


    val queryExecutor = new QueryExecutor


    val constructQueriesList =Source.fromFile(filePath).getLines
    var nb_req = 0
    var nb_model_notnull = 0
    var nb_model_null = 0

    constructQueriesList.grouped(10000).foreach {

      groupOfLines => {
        val timeFor100000 = System.currentTimeMillis()


        val treatedGroupOfLines = groupOfLines.par.map {

          line => {
            try {
              nb_req += 1
              println("Requete\t" + nb_req)


              val query = QueryFactory.create(line)

              runQuery(endPoint, queryExecutor, query).map {
                case model => {
                  nb_model_notnull += 1
                  Right(model)
                }
                case null => {
                  nb_model_null += 1
                  Left(line)
                }

              }.recover { case e: Exception => {
                Left(line)
              }
              }
            }catch {
              case ex: Exception => {
                Future.successful(Left(line))
              }
            }

          }


        }.toVector

        println("--------------------- un group finished ---------------------------------- ")

        val seq = Await.result(Future.sequence(treatedGroupOfLines), Duration.Inf)
        val (correct, errors) = seq.partition(_.isRight)


        println("************ nombre model not null avant tdb : " + nb_model_notnull)
        println("************ nombre model  null avant tdb : " + nb_model_null)


        writeInTdb(correct.collect { case Right(x) => x })
        // writeInTdb(correct.collect { case Right(x) => x })
        writeInLogFile(Declarations.paths.get("executionLogFile"), errors.collect { case Left(line) => line })

        val finish = System.currentTimeMillis() - timeFor100000
        println("time for 100 000 req is   " + finish)


      }

    }
  }

  def runQuery(endPoint: String, queryExecutor: QueryExecutor, query: Query): Future[Model] = future {
    //def runQuery(endPoint: String, queryExecutor: QueryExecutor, query: Query): Future[ResultSet] = future {
    numQueryRun += 1
    var model = ModelFactory.createDefaultModel()
    println("run query n: " + numQueryRun)
    try {
     // println(query.toString().replace("\n"," "))
      // model = queryExecutor.executeQueryConstruct(query, endPoint)
      model = executeQueryConstruct(query,endPoint)
    }
    catch {
      case e : Exception => throw e
    }
    // val model = queryExecutor.executeQuerySelect(query, endPoint)

    model
  }


  def executeQueryConstruct(query: Query, endpoint: String) = {
    var results = ModelFactory.createDefaultModel()
    try {
      val qexec = new QueryEngineHTTP(endpoint,query)
      qexec.setTimeout(60,TimeUnit.SECONDS, 60, TimeUnit.SECONDS)
      results = qexec.execConstruct
      qexec.close()
    } catch {
      case e: Exception =>
        System.out.println(e.getMessage)
        throw e
    }
    results
  }

  println(duration)

}
