package Services

import java.util

import Services.MDfromLogQueries.Declarations.Declarations
import Services.MDfromLogQueries.Util.TdbOperation
import com.google.common.base.Stopwatch
import org.apache.jena.query.Dataset
import org.apache.jena.rdf.model.Model
import org.apache.jena.tdb.TDBFactory

import Services.MDPatternDetection.ConsolidationClasses.ConsolidationParallel._

import scala.collection.mutable

object Test extends App {


  val stopwatchSelect: Stopwatch = Stopwatch.createStarted

  val endpoint = "DogFood"

  Declarations.setEndpoint(endpoint)


  val destination_dataset: Dataset = TDBFactory.createDataset(Declarations.paths.get("dataSetAlleviated20"))


  var nb_models = 0

  val models: util.HashMap[String, Model] = TdbOperation.unpersistModelsMap(Declarations.paths.get("dataSetAlleviated2"))
  val models_destination = new mutable.HashMap[String, Model]()
  val models_scala = convertToScalaMap(models)


  models_scala.foreach(pair=> {
    if (pair._2.size <= 20) {
      models_destination.put(pair._1, pair._2)
      nb_models += 1
    }
  })

  writeInTdb(models_scala, Declarations.paths.get("dataSetAlleviated20"))
  System.out.println("nb_models == \t" + nb_models)
  stopwatchSelect.stop

  System.out.println(" Temps de transformation " + stopwatchSelect)


}
