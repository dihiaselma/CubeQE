package Services.MDPatternDetection.EnrichmentClasses

import java.util

import Services.MDPatternDetection.ConsolidationClasses.ConsolidationParallel.{convertToScalaMap, writeInTdb}
import Services.MDfromLogQueries.Declarations.Declarations
import Services.MDfromLogQueries.Util.FileOperation.writeEnrichStatisticsListInYAMLFile
import Services.MDfromLogQueries.Util.{Constants2, ConstantsUtil, TdbOperation}
import org.apache.jena.rdf.model.Model

import scala.collection.mutable

object EnrichParallel   {

  var constantsUtil = new ConstantsUtil
  var constants2 = new Constants2()
  def enrichMDSchema(models: util.HashMap[String, Model], endpoint : String): Unit = {


    val modelsAnnotatedScala: mutable.HashMap[String, Model] = convertToScalaMap(models)
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
  }
}
