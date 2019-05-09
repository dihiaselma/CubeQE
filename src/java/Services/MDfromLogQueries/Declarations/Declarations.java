package Services.MDfromLogQueries.Declarations;


import Services.MDfromLogQueries.Util.FileOperation;

import java.util.HashMap;

public class Declarations {

    public static String endpoint;
    public static HashMap<String,String> paths = new HashMap<>();
    // public static String root = "C:\\Users\\KamilaB\\Desktop\\3CS\\Prototypage\\Step_1\\endpoints\\"+endpoint+"\\Support_Files\\";
    //public static String root2 = "C:\\Users\\KamilaB\\Desktop\\3CS\\Prototypage\\Step_1\\endpoints\\"+endpoint+"\\Support_Files\\TdbDirectories\\";


    public static String root;
    public static String rootTdb;


    public static String rootScenarioAnalytic = "Analytic\\";
    public static String statististicsRoot = "Statistics\\";


    public static void setEndpoint(String endpoint) {
        Declarations.endpoint = endpoint;
        root = "C:\\Users\\KamilaB\\Desktop\\3CS\\Prototypage\\Step_1\\endpoints\\"+endpoint+"\\Support_Files\\";
        rootTdb = "C:\\Users\\KamilaB\\Desktop\\3CS\\Prototypage\\Step_1\\endpoints\\"+endpoint+"\\Support_Files\\TdbDirectories\\";

        /* Queries log Path **/
        paths.put("directoryPath",root+ "Data Log\\logs");

        /* Step 1 : Log Cleaning **/
        paths.put("cleanedQueriesFile",root + "ProgramOutput\\Fichier_log_Nettoye_Complet_Parallel.txt");
        paths.put("cleanedQueriesFileCopie",root + "ProgramOutput\\Fichier_log_Nettoye_Complet_Parallel_Scala_Copie.txt");
        paths.put("notCleanedQueries",root + "ProgramOutput\\not_Cleaned_Queries_File.txt");

        /* Step 2 : Queries deduplication **/
        paths.put("writingDedupFilePath",root + "ProgramOutput\\Fichier_log_dedup_Nettoye_java.txt");

        /* Step 3 : Syntactical Validation **/
        paths.put("syntaxValidFile",root + "ProgramOutput\\Fichier_Syntaxe_Valide.txt");
        paths.put("syntaxValidFile2",root + "ProgramOutput\\Fichier_Syntaxe_Valide_scala.txt");
        paths.put("syntaxNonValidFile",root + "ProgramOutput\\Fichier_Syntaxe_Non_Valide.txt");
        paths.put("syntaxNonValidFile2",root + "ProgramOutput\\Fichier_Syntaxe_Non_Valide_scala.txt");

        /* Step 4 : Add rdf:type variables and transform queries to Construct **/
        paths.put("logFile",root + "ProgramOutput\\Fichier_log.txt");
        paths.put("constructQueriesFile",root + "ProgramOutput\\Fichier_Construct_Queries.txt");
        paths.put("constructQueriesFile2",root + "ProgramOutput\\Fichier_Construct_Queries_scala.txt");
        paths.put("constructLogFileParallel",root + "ProgramOutput\\Fichier_log_Constuct_scala.txt");
        paths.put("constructLogFile",root + "ProgramOutput\\Fichier_log_construct_java.txt");

        /* Step 5 : Execution of queries **/
        paths.put("executionLogFile",root + "ProgramOutput\\Fichier_not_Executed_queries.txt");


        /* Resulting models statistics **/
        paths.put("statisticsFile",root + statististicsRoot + "Fichier_statistique.txt");
        paths.put("minstatisticsFile",root + statististicsRoot + "Fichier_statistique_min.txt");
        paths.put("maxstatisticsFile ",root + statististicsRoot + "Fichier_statistique_max.txt");
        paths.put("avgstatisticsFile",root + statististicsRoot + "Fichier_statistique_avg.txt");
        paths.put("totalstatisticsFile",root + statististicsRoot + "Fichier_statistique_total.txt");
        paths.put("statisticsAnalyticFactFile",root + statististicsRoot + "Fichier_statistique_Analytique_Fact.txt");
        paths.put("statisticsAnalyticDimFile",root + statististicsRoot + "Fichier_statistique_Analytique_Dimension.txt");

        /* Default ontologies directory **/
        paths.put("defaultOntologiesDir",root + "Ontologies");
        paths.put("propertiesOntology",root + "ontologies_namespaces\\addedOntology.ttl");



        /* Triple databases directories **/
        paths.put("tdbDirectory", rootTdb + "tdbDirectory");
        paths.put("dataSetOriginal", rootTdb + "dataSetOriginal");
        paths.put("dataSetConsolidated", rootTdb + "dataSetConsolidated");
        paths.put("dataSetAnnotated", rootTdb + "dataSetAnnotated");
        paths.put("_toString", rootTdb + "_toString");
        paths.put("dataSetAnalytic", rootTdb + "dataSetAnalytic");
        paths.put("dataSetAnalyticAnnotated", rootTdb + "dataSetAnalyticAnnotated");
        paths.put("dataSetAlleviated", rootTdb + "dataSetAlleviated");
        paths.put("dataSetNonAlleviated", rootTdb + "dataSetNonAlleviated");
        paths.put("dataSetAlleviatedUselessProperties", rootTdb + "dataSetAlleviatedUselessProperties");
        paths.put("dataSetEnriched", rootTdb + "dataSetEnriched");


        /* Scénario 2 : Analytic Queries **/
        paths.put("AggregQueriesFile",root + rootScenarioAnalytic + "ProgramOutput\\Fichier_Analytic_Queries.txt");
        paths.put("AnalyticQueriesFile",root + rootScenarioAnalytic + "ProgramOutput\\Fichier_Analytic_Queries2.txt");
        paths.put("AnalyticSelectQueriesFile",root + rootScenarioAnalytic + "ProgramOutput\\Fichier_Analytic_Select_Queries.txt");
        paths.put("SelectQueriesAndResult",root + rootScenarioAnalytic + "ProgramOutput\\Fichier_Analytic_Select_Queries_Results.txt");


        /* Execution time of programs **/
        paths.put("timesFilePath",root + "ProgramOutput\\Fichier_Temps.yaml");
        paths.put("timesFilePathTest",root + "ProgramOutput\\Fichier_TempsTest.yaml");
        paths.put("queriesNumberFilePath",root + "ProgramOutput\\Fichier_queriesNumber.yaml");
        paths.put("queriesNumberFilePathTest",root + "ProgramOutput\\Fichier_queriesNumberTest.yaml");

        /*for (String key : paths.keySet())
        {
            FileOperation.createDirectory(paths.get(key));
        }*/

    }

    public static String getEndpoint() {
        return endpoint;
    }


}