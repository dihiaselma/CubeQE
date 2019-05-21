package Services.MDPatternDetection.EnrichmentClasses

import java.util

import Services.MDPatternDetection.ConsolidationClasses.ConsolidationParallel.{convertToScalaMap, writeInTdb}
import Services.MDfromLogQueries.Declarations.Declarations
import Services.MDfromLogQueries.Util.FileOperation.writeEnrichStatisticsListInYAMLFile
import Services.MDfromLogQueries.Util.{Constants2, ConstantsUtil, TdbOperation}
import org.apache.jena.rdf.model.Model

import scala.collection.mutable

object EnrichParallel extends App {

  val modelsAnnotated: util.HashMap[String, Model] = TdbOperation.unpersistModelsMap(TdbOperation.dataSetAnnotated)
  var endpoint = "https://dbpedia.org/sparql"
  var constantsUtil = new ConstantsUtil
  var constants2 = new Constants2()

  enrichMDSchema(modelsAnnotated)

  def enrichMDSchema(models: util.HashMap[String, Model]): Unit = {


    val modelsAnnotatedScala: mutable.HashMap[String, Model] = convertToScalaMap(modelsAnnotated)
    val itModels = modelsAnnotatedScala.keys
    var numModel = 0

    modelsAnnotatedScala.grouped(20000).foreach{
      groupOfModels =>{
        val time = System.currentTimeMillis()
        val treatedGroupOfLines = groupOfModels.par.map{
          pair =>{
            numModel+=1
            println(s"le model num: $numModel")
            Enrich.enrichModel(pair._2)
            pair
          }
        }

        writeInTdb(treatedGroupOfLines.seq,Declarations.paths.get("dataSetEnriched"))
        writeEnrichStatisticsListInYAMLFile(Enrich.statisticsAnalytics4Fact, Declarations.paths.get("statisticsAnalyticFactFile"))
        writeEnrichStatisticsListInYAMLFile(Enrich.statisticsAnalytics4Dimension, Declarations.paths.get("statisticsAnalyticDimFile"))
      }
    }
    /*itModels.grouped(20000).foreach {
      groupOfmodels => {
        val time = System.currentTimeMillis()
        val treatedGroupOfLines = groupOfmodels.par.foreach {
          key => {
            numModel += 1
            println(s"le model num: $numModel")
            Enrich.enrichModel(modelsAnnotatedScala(key))
            Left(key, modelsAnnotatedScala(key))

          }
        }
        writeInTdb(convertToScalaMap(),TdbOperation.dataSetEnriched)
        writeStatisticsListInFile2(Enrich.statisticsAnalytics4Fact, Declarations.paths.get("statisticsAnalyticFactFile"))
        writeStatisticsListInFile2(Enrich.statisticsAnalytics4Dimension, Declarations.paths.get("statisticsAnalyticDimFile"))

      }

    }

  }*/

  }
}
