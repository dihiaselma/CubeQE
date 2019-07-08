package Services.Scenarios

import Services.MDPatternDetection.AnalyticalQueriesClasses.AnalyticQueriesScala
import Services.MDfromLogQueries.Declarations.Declarations
import Services.MDfromLogQueries.Util.{FileOperation, TdbOperation}
import Services.Statistics.Statistics1


object Scenario_Analytic extends App{


  val endpoint="DogFood"
  val endpointUrl="http://www.scholarlydata.org/sparql/"

  Declarations.setEndpoint(endpoint)

  var t_analytic: Long = System.currentTimeMillis()



/*
  /** 1. Extraction des requêtes analytiques du fichier SyntaxValid */
  var t_extraction: Long = System.currentTimeMillis()
  val queryList = FileOperation.ReadFile(Declarations.paths.get("syntaxValidFile2")).asInstanceOf[util.ArrayList[String]]
  val analyticQueriesList = AnalyticQueries.getAnalyticQueries(queryList)
  FileOperation.WriteInFile(Declarations.paths.get("AnalyticQueriesFile"), analyticQueriesList)
  FileOperation.writeInYAMLFile(Declarations.paths.get("queriesNumberFilePath"), "Analytic_Queries", AnalyticQueries.queriesNumber)
  println("analytic queries number : "+ AnalyticQueries.queriesNumber)
  FileOperation.writeInYAMLFile(Declarations.paths.get("timesFilePath"), "Analytic_extraction", (System.currentTimeMillis() - t_extraction).toInt)

  /** 2. Execution des requetes */
  var t_executing: Long = System.currentTimeMillis()
  AnalyticQueriesScala.executeAnalyticQueriesList(endpoint)
  FileOperation.writeInYAMLFile(Declarations.paths.get("timesFilePath"), "Analytic_execution", (System.currentTimeMillis() - t_executing).toInt)


/** 3. Consolidation **/
  var
    / t_consolidation: Long = System.currentTimeMillis()
  ConsolidationParallel.toStringModelsHashmap2(
    unpersistModelsMap(Declarations.paths.get("dataSetAnalytic")), Declarations.paths.get("_toStringAnalytic"))
  Fil
eOperation.writeInYAMLFile(Declarations.paths.get("timesFilePath"), "Analytic_consolidation", (System.currentTimeMillis() - t_consolidation).toInt)
  */
  /** 4. Annotation des requêtes analytiques */
  var t_annotation : Long = System.currentTimeMillis()
  AnalyticQueriesScala.AnalyticQueriesAnnotation()
  FileOperation.writeInYAMLFile(Declarations.paths.get("timesFilePath"), "Analytic_annotation", (System.currentTimeMillis() - t_annotation).toInt)


  /** 5. Statistics  **/
  var t_statistics: Long = System.currentTimeMillis()
  var statistics : Statistics1 = new Statistics1
  val stat = statistics.stat2(TdbOperation.unpersistModelsMap(Declarations.paths.get("dataSetAnalyticAnnotated")))
  Statistics1.writeAllStatsInYAML(stat,Declarations.paths.get("analyticStatisticsFileYAML"),Declarations.paths.get("analyticStatisticsByTypeFile"))
  //statisticsBySubjectList(subjects)
  FileOperation.writeInYAMLFile(Declarations.paths.get("timesFilePath"), "StatisticsAnalytic", (System.currentTimeMillis() - t_statistics).toInt)
  FileOperation.writeInYAMLFile(Declarations.paths.get("timesFilePath"), "Analytic_process", (System.currentTimeMillis() - t_analytic).toInt)


}
