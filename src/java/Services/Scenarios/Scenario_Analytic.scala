package Services.Scenarios

import java.util

import Services.MDPatternDetection.AnalyticalQueriesClasses.{AnalyticQueries, AnalyticQueriesScala}
import Services.MDfromLogQueries.Declarations.Declarations
import Services.MDfromLogQueries.Declarations.Declarations.{queriesNumberFilePath, timesFilePath}
import Services.MDfromLogQueries.LogCleaning.LogCleaning
import Services.MDfromLogQueries.Util.FileOperation


object Scenario_Analytic extends App{

  val endpoint = "https://dbpedia.org/sparql"

  /** 1. Extraction des requêtes analytiques du fichier SyntaxValid et execution */
  var t_extraction: Long = System.currentTimeMillis()
  val queryList = FileOperation.ReadFile(Declarations.syntaxValidFile2).asInstanceOf[util.ArrayList[String]]
  val analyticQueriesList = AnalyticQueries.getAnalyticQueries(queryList)
  FileOperation.WriteInFile(Declarations.AnalyticQueriesFile, analyticQueriesList)
  FileOperation.writeInYAMLFile(queriesNumberFilePath, "Analytic_Queries", AnalyticQueries.queriesNumber)
  FileOperation.writeInYAMLFile(timesFilePath, "Analytic_extraction", (System.currentTimeMillis() - t_extraction).toInt)



  /** 2. Execution des requetes */
  var t_executing: Long = System.currentTimeMillis()
  AnalyticQueriesScala.executeAnalyticQueriesList(endpoint)
  FileOperation.writeInYAMLFile(timesFilePath, "Analytic_execution", (System.currentTimeMillis() - t_executing).toInt)


  /** 3. Annotation des requêtes analytiques */
  var t_annotation : Long = System.currentTimeMillis()
  AnalyticQueriesScala.AnalyticQueriesAnnotation()
  FileOperation.writeInYAMLFile(timesFilePath, "Analytic_annotation", (System.currentTimeMillis() - t_annotation).toInt)




}
