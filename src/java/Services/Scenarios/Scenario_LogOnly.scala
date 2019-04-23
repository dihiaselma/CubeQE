package Services.Scenarios

import java.util

import Services.MDPatternDetection.AnnotationClasses.MDGraphAnnotated
import Services.MDPatternDetection.ConsolidationClasses.ConsolidationParallel
import Services.MDPatternDetection.ConsolidationClasses.ConsolidationParallel._
import Services.MDPatternDetection.ExecutionClasses.QueryExecutor
import Services.MDPatternDetection.ExecutionClasses.QueryExecutor.executeQuiersInFile
import Services.MDfromLogQueries.Declarations.Declarations._
import Services.MDfromLogQueries.LogCleaning.QueriesDeduplicator.DeduplicateQueriesInFile
import Services.MDfromLogQueries.LogCleaning.LogCleaning._
import Services.MDfromLogQueries.LogCleaning.{LogCleaning, QueriesDeduplicator}
import Services.MDfromLogQueries.SPARQLSyntacticalValidation.SyntacticValidationParallel
import Services.MDfromLogQueries.SPARQLSyntacticalValidation.SyntacticValidationParallel.valideQueriesInFile
import Services.MDfromLogQueries.Util.{FileOperation, TdbOperation}
import Services.Statistics.MDGraphBySubjectScala.{statisticsBySubjectList, subjects}
import org.apache.jena.rdf.model.Model



object Scenario_LogOnly extends App{

  /** 1. Nettoyage du log **/
  var t_cleaning: Long = System.currentTimeMillis()
  writeFiles(directoryPath, cleanedQueriesFile)
  FileOperation.writeTimesInFile(timesFilePath, "Log Cleaning", System.currentTimeMillis() - t_cleaning)
  FileOperation.writeQueriesNumberInFile(queriesNumberFilePath, "Log Cleaning", LogCleaning.queriesNumber)

  /** 2. Deduplication **/
  var t_dedup: Long = System.currentTimeMillis()
  DeduplicateQueriesInFile(cleanedQueriesFile)
  FileOperation.writeTimesInFile(timesFilePath, "Deduplication ", System.currentTimeMillis() - t_dedup)
  FileOperation.writeQueriesNumberInFile(queriesNumberFilePath, "Deduplication ", QueriesDeduplicator.queriesNumber)


  /** 3. Validaion syntaxique **/
  var t_syntacticValidation: Long = System.currentTimeMillis()
  valideQueriesInFile(writingDedupFilePath)
  FileOperation.writeTimesInFile(timesFilePath, "Syntactical Validation ", System.currentTimeMillis() - t_syntacticValidation)
  FileOperation.writeQueriesNumberInFile(queriesNumberFilePath, "Syntactical Validation ", SyntacticValidationParallel.queriesNumber )

  /** 4. Execution **/
  var t_execution: Long = System.currentTimeMillis()
  val endpoint="https://dbpedia.org/sparql"
  executeQuiersInFile(constructQueriesFile2, endpoint)
  FileOperation.writeTimesInFile(timesFilePath, "Execution ", System.currentTimeMillis() - t_execution)
  FileOperation.writeQueriesNumberInFile(queriesNumberFilePath, "Execution : nb queries executed ", QueryExecutor.queriesNumber)
  FileOperation.writeQueriesNumberInFile(queriesNumberFilePath, "Execution : nb queries non executed ", QueryExecutor.queriesLogNumber)

  /** 5. Consolidation **/
  var t_consolidation: Long = System.currentTimeMillis()
  writeInTdb(consolidate(TdbOperation.unpersistModelsMap(TdbOperation.originalDataSet)), TdbOperation.dataSetConsolidate)
  FileOperation.writeTimesInFile(timesFilePath, "Consolidation ", System.currentTimeMillis() - t_consolidation)
  FileOperation.writeQueriesNumberInFile(queriesNumberFilePath, "Consolidation : nb of models  ", ConsolidationParallel.modelsNumber)


  /** 6. Annotation **/
  var t_annotation: Long = System.currentTimeMillis()
  val modelsConsolidated: util.HashMap[String, Model] = TdbOperation.unpersistModelsMap(TdbOperation.dataSetConsolidate)
  val modelsAnnotated : util.HashMap[String, Model] = MDGraphAnnotated.constructMDGraphs(modelsConsolidated)
  writeInTdb(convertToScalaMap(modelsAnnotated), TdbOperation.dataSetAnnotated)
  FileOperation.writeTimesInFile(timesFilePath, "Annotation ", System.currentTimeMillis() - t_annotation)


  /** 7. Statistique **/
  var t_statistics: Long = System.currentTimeMillis()
  statisticsBySubjectList(subjects)
  FileOperation.writeTimesInFile(timesFilePath, "Statistics ", System.currentTimeMillis() - t_statistics)
  //TODO ecrire dans un fichier les stat concernant nombre de req ..Etc
}
