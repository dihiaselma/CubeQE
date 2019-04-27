package Services.Scenarios

import java.util

import Services.MDPatternDetection.AnnotationClasses.MDGraphAnnotated
import Services.MDPatternDetection.ConsolidationClasses.ConsolidationParallel
import Services.MDPatternDetection.ConsolidationClasses.ConsolidationParallel._
import Services.MDPatternDetection.ExecutionClasses.QueryExecutor
import Services.MDPatternDetection.ExecutionClasses.QueryExecutor.executeQuiersInFile
import Services.MDPatternDetection.GraphConstructionClasses.Queries2GraphesParallel
import Services.MDfromLogQueries.Declarations.Declarations._
import Services.MDfromLogQueries.LogCleaning.QueriesDeduplicator.DeduplicateQueriesInFile
import Services.MDfromLogQueries.LogCleaning.LogCleaning._
import Services.MDfromLogQueries.LogCleaning.{LogCleaning, QueriesDeduplicator}
import Services.MDfromLogQueries.SPARQLSyntacticalValidation.SyntacticValidationParallel
import Services.MDfromLogQueries.SPARQLSyntacticalValidation.SyntacticValidationParallel.valideQueriesInFile
import Services.MDfromLogQueries.Util.{FileOperation, TdbOperation}
import Services.MDPatternDetection.GraphConstructionClasses.Queries2GraphesParallel.TransformQueriesInFile
import Services.Statistics.MDGraphBySubjectScala.{statisticsBySubjectList, subjects}
import org.apache.jena.rdf.model.Model



object Scenario_LogOnly extends App{

  /** 1. Nettoyage du log **/
  var t_cleaning: Long = System.currentTimeMillis()
  writeFiles(directoryPath, cleanedQueriesFile)
  FileOperation.writeInYAMLFile(timesFilePath, "Log_Cleaning", (System.currentTimeMillis() - t_cleaning).toInt)
  FileOperation.writeInYAMLFile(queriesNumberFilePath, "Log_Cleaning_nbLines", LogCleaning.nb_queries)
  FileOperation.writeInYAMLFile(queriesNumberFilePath, "Log_Cleaning", LogCleaning.queriesNumber)

  /** 2. Deduplication **/
  var t_dedup: Long = System.currentTimeMillis()
  DeduplicateQueriesInFile(cleanedQueriesFile)
  FileOperation.writeInYAMLFile(timesFilePath, "Deduplication", (System.currentTimeMillis() - t_dedup).toInt)
  FileOperation.writeInYAMLFile(queriesNumberFilePath, "Deduplication", QueriesDeduplicator.queriesNumber)

  /** 3. Validaion syntaxique **/
  var t_syntacticValidation: Long = System.currentTimeMillis()
  valideQueriesInFile(writingDedupFilePath)
  FileOperation.writeInYAMLFile(timesFilePath, "Syntactical_Validation", (System.currentTimeMillis() - t_syntacticValidation).toInt)
  FileOperation.writeInYAMLFile(queriesNumberFilePath, "Syntactical_Validation", SyntacticValidationParallel.queriesNumber )

  /** 4. Construct MD graphs **/
  var t_connstructMDgraphs: Long = System.currentTimeMillis()
  TransformQueriesInFile(writingDedupFilePath)
  FileOperation.writeInYAMLFile(timesFilePath, "ConstructMSGraphs", (System.currentTimeMillis() -  t_connstructMDgraphs).toInt)
  FileOperation.writeInYAMLFile(queriesNumberFilePath, "ConstructMSGraphs_nbQueriesConstructed", Queries2GraphesParallel.queriesNumber)
  FileOperation.writeInYAMLFile(queriesNumberFilePath, "ConstructMSGraphs_nbQueriesNonConstructed", Queries2GraphesParallel.queriesNumberNonConstructed)


  /** 5. Execution **/
  var t_execution: Long = System.currentTimeMillis()
  val endpoint="https://dbpedia.org/sparql"
  executeQuiersInFile(constructQueriesFile2, endpoint)
  FileOperation.writeInYAMLFile(timesFilePath, "Execution", (System.currentTimeMillis() - t_execution).toInt)
  FileOperation.writeInYAMLFile(queriesNumberFilePath, "Execution_nbQueriesExecuted", QueryExecutor.queriesNumber)
  FileOperation.writeInYAMLFile(queriesNumberFilePath, "Execution_nbQueriesNonExecuted", QueryExecutor.queriesLogNumber)

  /** 6. Consolidation **/
  var t_consolidation: Long = System.currentTimeMillis()
  writeInTdb(consolidate(TdbOperation.unpersistModelsMap(TdbOperation.originalDataSet)), TdbOperation.dataSetConsolidate)
  FileOperation.writeInYAMLFile(timesFilePath, "Consolidation", (System.currentTimeMillis() - t_consolidation).toInt)
  FileOperation.writeInYAMLFile(queriesNumberFilePath, "Consolidation_nbModelsNonConsolidated", ConsolidationParallel.originalModelsNumber)
  FileOperation.writeInYAMLFile(queriesNumberFilePath, "Consolidation_nbModels", ConsolidationParallel.modelsNumber)


  /** 7. Annotation **/
  var t_annotation: Long = System.currentTimeMillis()
  val modelsConsolidated: util.HashMap[String, Model] = TdbOperation.unpersistModelsMap(TdbOperation.dataSetConsolidate)
  val modelsAnnotated : util.HashMap[String, Model] = MDGraphAnnotated.constructMDGraphs(modelsConsolidated)
  writeInTdb(convertToScalaMap(modelsAnnotated), TdbOperation.dataSetAnnotated)
  FileOperation.writeInYAMLFile(timesFilePath, "Annotation", (System.currentTimeMillis() - t_annotation).toInt)


  /** 8. Statistique **/
  var t_statistics: Long = System.currentTimeMillis()
  statisticsBySubjectList(subjects)
  FileOperation.writeInYAMLFile(timesFilePath, "Statistics", (System.currentTimeMillis() - t_statistics).toInt)


  //TODO ecrire dans un fichier les stat concernant nombre de req ..Etc
}
