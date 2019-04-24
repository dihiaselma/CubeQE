package Services.Scenarios

import Services.MDPatternDetection.AnalyticalQueriesClasses.AnalyticQueries
import Services.MDfromLogQueries.Declarations.Declarations.{queriesNumberFilePath, timesFilePath}
import Services.MDfromLogQueries.LogCleaning.LogCleaning
import Services.MDfromLogQueries.Util.FileOperation


class Scenario_Analytic extends App{

  /** 1. Extraction des requêtes analytiques du fichier SyntaxValid et execution */
  var t_processing: Long = System.currentTimeMillis()
  AnalyticQueries.AnalyticQueriesProcessing()
  FileOperation.writeInYAMLFile(timesFilePath, "Analytic_processing", (System.currentTimeMillis() - t_processing).toInt)
  FileOperation.writeInYAMLFile(queriesNumberFilePath, "Analytic_Queries", AnalyticQueries.queriesNumber)


  /** 2. Annotation des requêtes analytiques */
  var t_annotation : Long = System.currentTimeMillis()
  AnalyticQueries.AnalyticQueriesAnnotation()
  FileOperation.writeInYAMLFile(timesFilePath, "Analytic_annotation", (System.currentTimeMillis() - t_annotation).toInt)




}
