package Services.Scenarios

import java.util

import Services.MDPatternDetection.AnnotationClasses.MDGraphAnnotated
import Services.MDPatternDetection.EnrichmentClasses.EnrichParallel.enrichMDSchema
import Services.MDfromLogQueries.Declarations.Declarations
import Services.MDfromLogQueries.Util.{FileOperation, TdbOperation}
import org.apache.jena.rdf.model.Model

object Scenario_Enrichment extends App{


 
  /** 1. Unpersisting of annotated models **/
  var t_unpersisting: Long = System.currentTimeMillis()
  val modelsAnnotated: util.HashMap[String, Model] = TdbOperation.unpersistModelsMap(TdbOperation.dataSetAnnotated)
  FileOperation.writeInYAMLFile(Declarations.paths.get("timesFilePath"), "Enrichment_Unpersisting", (System.currentTimeMillis() - t_unpersisting).toInt)
  FileOperation.writeInYAMLFile(Declarations.paths.get("queriesNumberFilePath"),"Enrichment_Unpersisting",modelsAnnotated.size())


  /** 2. Annotate non alleviated models **/
  var t_annotation: Long = System.currentTimeMillis()
  val modelsNonAlleviated: util.HashMap[String, Model] = TdbOperation.unpersistModelsMap(TdbOperation.dataSetNonAlleviated)
  val modelsNonAlleviatedAnnotated : util.HashMap[String, Model] = MDGraphAnnotated.constructMDGraphs(modelsNonAlleviated)
  FileOperation.writeInYAMLFile(Declarations.paths.get("timesFilePath"), "modelsNonAlleviatedAnnotation", (System.currentTimeMillis() - t_annotation).toInt)
  modelsAnnotated.putAll(modelsNonAlleviatedAnnotated)


  /** 3. Enrichment of annotated models **/
  var t_enrichment: Long = System.currentTimeMillis()
  enrichMDSchema(modelsAnnotated)
  FileOperation.writeInYAMLFile(Declarations.paths.get("timesFilePath"), "Enrichment", (System.currentTimeMillis() - t_enrichment).toInt)


}
