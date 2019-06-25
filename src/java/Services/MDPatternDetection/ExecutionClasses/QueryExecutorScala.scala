package Services.MDPatternDetection.ExecutionClasses

import java.util.concurrent.TimeUnit

import Services.MDfromLogQueries.Declarations.Declarations
import Services.MDfromLogQueries.Util.FileOperation
import org.apache.jena.query.{Query, QueryExecution, QueryExecutionFactory, ResultSet}

object QueryExecutorScala {
  def executeQuerySelect(query: Query, endpoint: String): ResultSet = {
    var results : ResultSet = null
    try {
      val qexec = QueryExecutionFactory.sparqlService(endpoint, query)
      qexec.setTimeout(50, TimeUnit.SECONDS, 50, TimeUnit.SECONDS)
      results = qexec.execSelect
      //qexec.close()
    } catch {
      case e: Exception =>
        System.out.println(e.getMessage)
    }
    results
  }

}
