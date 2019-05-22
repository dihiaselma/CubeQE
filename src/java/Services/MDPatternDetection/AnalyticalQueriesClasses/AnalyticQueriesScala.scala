package Services.MDPatternDetection.AnalyticalQueriesClasses

import java.util
import java.util.{ArrayList, HashMap}

import Services.MDPatternDetection.AnalyticalQueriesClasses.AnalyticQueries
import Services.MDPatternDetection.AnnotationClasses.MDGraphAnnotated
import Services.MDPatternDetection.ConsolidationClasses.ConsolidationParallel
import Services.MDfromLogQueries.Declarations.Declarations
import Services.MDfromLogQueries.Util.{Constants2, TdbOperation}
import org.apache.jena.query.Dataset
import org.apache.jena.rdf.model.Model
import org.apache.jena.tdb.TDB

import scala.collection.{JavaConverters, mutable}
import scala.io.Source


object AnalyticQueriesScala  {

  var nb_model = 0


  def executeAnalyticQueriesList(endpoint: String)={
    var nb_line = 0
    //for statistical needs
    var nb = 0
    val modelHashSet = new util.HashSet[Model]

    val QueriesList = Source.fromFile(Declarations.paths.get("AnalyticQueriesFile")).getLines

    QueriesList.grouped(50).foreach {

      groupOfLines => {

        val treatedGroupOfLines = groupOfLines.par.map {

          line => {
            try {
              nb_line += 1
              //queryStr = line;
              println("requete num : " + nb_line)
              modelHashSet.addAll(AnalyticQueries.executeAnalyticQuery(line, endpoint))
            } catch {
              case e: Exception =>
                e.printStackTrace()
                System.out.println("erreur")
                nb += 1
            }
          }
        }
        println("--------------------- un group finished ---------------------------------- ")
        writeInTdb(JavaConverters.asScalaSet(modelHashSet),TdbOperation.dataSetAnalytic)
        modelHashSet.clear()

      }
    }
  }


  def AnalyticQueriesAnnotation(): Unit = {
    new Constants2
    new TdbOperation
    var modelHashMap = new util.HashMap[String,Model]
    modelHashMap = TdbOperation.unpersistModelsMap(TdbOperation.dataSetAnalytic)
    var modelHashMapAnnotated = new util.HashMap[String, Model]
    if (modelHashMap != null) modelHashMapAnnotated = MDGraphAnnotated.constructMDGraphs(modelHashMap)
    writeInTdb(ConsolidationParallel.convertToScalaMap(modelHashMapAnnotated),TdbOperation.dataSetAnalyticAnnotated)
   // TdbOperation.persistHashMap(modelHashMapAnnotated, TdbOperation.dataSetAnalyticAnnotated)

  }

  def writeInTdb(models: mutable.Set[Model], dataset: Dataset) = {

    models.foreach(m => {

      if (m != null) {
        nb_model += 1
        println("write " + nb_model)
        dataset
          //.originalDataSetTest
          .addNamedModel("model_" + nb_model,
          m)
      }
    })
    TDB.sync(dataset)

  }
  def writeInTdb(models: mutable.Map[String,Model], dataset: Dataset) = {

    val keys = models.keysIterator

    for (elem <- keys) {
      if (models.get(elem) != null)
        {
          nb_model +=1
          println("write " + nb_model)
          dataset
            //.originalDataSetTest
            .addNamedModel(elem,
            models(elem))
        }
    }

    TDB.sync(dataset)

  }



}
