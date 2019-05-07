package Services.MDfromLogQueries.Declarations;


public class Declarations {

    public static String endpoint;
    // public static String root = "C:\\Users\\KamilaB\\Desktop\\3CS\\Prototypage\\Step_1\\endpoints\\"+endpoint+"\\Support_Files\\";
    //public static String root2 = "C:\\Users\\KamilaB\\Desktop\\3CS\\Prototypage\\Step_1\\endpoints\\"+endpoint+"\\Support_Files\\TdbDirectories\\";


    public static String root;
    public static String root2;


    public static String rootScenarioAnalytic = "Analytic\\";
    public static String statististicsRoot = "Statistics\\";


    /**
     * Queries log Path
     **/
    public static String directoryPath;

    /**
     * Step 1 : Log Cleaning
     **/
    public static String cleanedQueriesFile;
    public static String cleanedQueriesFileCopie;
    public static String notCleanedQueries;


    /**
     * Step 2 : Queries deduplication
     **/
    public static String writingDedupFilePath;


    /**
     * Step 3 : Syntactical Validation
     **/
    public static String syntaxValidFile;
    public static String syntaxValidFile2;
    public static String syntaxNonValidFile;
    public static String syntaxNonValidFile2;


    /**
     * Step 4 : Add rdf:type variables and transform queries to Construct
     **/
    public static String logFile;
    public static String constructQueriesFile;
    public static String constructQueriesFile2;
    public static String constructLogFileParallel;
    public static String constructLogFile;

    /**
     * Step 5 : Execution of queries
     **/
    public static String executionLogFile;


    /**
     * Resulting models statistics
     **/
    public static String statisticsFile;
    public static String minstatisticsFile;
    public static String maxstatisticsFile;
    public static String avgstatisticsFile;
    public static String totalstatisticsFile;
    public static String statisticsAnalyticFactFile;
    public static String statisticsAnalyticDimFile;

    /**
     * Default ontologies directory
     **/
    public static String defaultOntologiesDir;

    /**
     * Triple databases directories
     **/
    public static String tdbDirectory;
    public static String dataSetOriginal;
    public static String dataSetConsolidated;
    public static String dataSetAnnotated;
    public static String _toString;
    public static String dataSetAnalytic;
    public static String dataSetAnalyticAnnotated;
    public static String dataSetAlleviated;
    public static String dataSetNonAlleviated;
    public static String dataSetAlleviatedUselessProperties;

    public static String propertiesOntology;
    public static String directoryPathCopie;
    public static String writingFilePath;
    public static String writingFilePathCopie;
    public static String modelsFilePath;
    public static String syntaxValidFileTest;


    /**
     * Scénario 2 : Analytic Queries
     **/
    public static String AggregQueriesFile;
    public static String AnalyticQueriesFile;
    public static String AnalyticSelectQueriesFile;
    public static String SelectQueriesAndResult;

    /**
     * Execution time of programs
     **/
    public static String timesFilePath;
    public static String timesFilePathTest;
    public static String queriesNumberFilePath;
    public static String queriesNumberFilePathTest;
    // public static String test = root + "ProgramOutput\\fich.json";


    public static void setEndpoint(String endpoint) {
        Declarations.endpoint = endpoint;

        root = "C:\\Users\\pc\\Desktop\\PFE\\Files\\" + endpoint + "\\";
        root2 = "E:\\TdbDirectories\\" + endpoint + "\\";


        /* Queries log Path **/
        directoryPath = root + "Data Log\\logs";

        /* Step 1 : Log Cleaning **/
        cleanedQueriesFile = root + "ProgramOutput\\Fichier_log_Nettoye_Complet_Parallel.txt";
        cleanedQueriesFileCopie = root + "ProgramOutput\\Fichier_log_Nettoye_Complet_Parallel_Scala_Copie.txt";
        notCleanedQueries = root + "ProgramOutput\\not_Cleaned_Queries_File.txt";


        /* Step 2 : Queries deduplication **/
        writingDedupFilePath = root + "ProgramOutput\\Fichier_log_dedup_Nettoye_java.txt";


        /* Step 3 : Syntactical Validation **/
        syntaxValidFile = root + "ProgramOutput\\Fichier_Syntaxe_Valide.txt";
        syntaxValidFile2 = root + "ProgramOutput\\Fichier_Syntaxe_Valide_scala.txt";
        syntaxNonValidFile = root + "ProgramOutput\\Fichier_Syntaxe_Non_Valide.txt";
        syntaxNonValidFile2 = root + "ProgramOutput\\Fichier_Syntaxe_Non_Valide_scala.txt";


        /* Step 4 : Add rdf:type variables and transform queries to Construct **/
        logFile = root + "ProgramOutput\\Fichier_log.txt";
        constructQueriesFile = root + "ProgramOutput\\Fichier_Construct_Queries.txt";
        constructQueriesFile2 = root + "ProgramOutput\\Fichier_Construct_Queries_scala.txt";
        constructLogFileParallel = root + "ProgramOutput\\Fichier_log_Constuct_scala.txt";
        constructLogFile = root + "ProgramOutput\\Fichier_log_construct_java.txt";

        /* Step 5 : Execution of queries **/
        executionLogFile = root + "ProgramOutput\\Fichier_not_Executed_queries.txt";


        /* Resulting models statistics **/
        statisticsFile = root + statististicsRoot + "Fichier_statistique.txt";
        minstatisticsFile = root + statististicsRoot + "Fichier_statistique_min.txt";
        maxstatisticsFile = root + statististicsRoot + "Fichier_statistique_max.txt";
        avgstatisticsFile = root + statististicsRoot + "Fichier_statistique_avg.txt";
        totalstatisticsFile = root + statististicsRoot + "Fichier_statistique_total.txt";
        statisticsAnalyticFactFile = root + statististicsRoot + "Fichier_statistique_Analytique_Fact.txt";
        statisticsAnalyticDimFile = root + statististicsRoot + "Fichier_statistique_Analytique_Dimension.txt";

        /* Default ontologies directory **/
        defaultOntologiesDir = root + "Ontologies";

        /* Triple databases directories **/
        tdbDirectory = root2 + "tdbDirectory";
        dataSetOriginal = root2 + "dataSetOriginal";
        dataSetConsolidated = root2 + "dataSetConsolidated";
        dataSetAnnotated = root2 + "dataSetAnnotated";
        _toString = root2 + "_toString";
        dataSetAnalytic = root2 + "dataSetAnalytic";
        dataSetAnalyticAnnotated = root2 + "dataSetAnalyticAnnotated";
        dataSetAlleviated = root2 + "dataSetAlleviated";
        dataSetNonAlleviated = root2 + "dataSetNonAlleviated";
        dataSetAlleviatedUselessProperties = root2+"dataSetAlleviatedUselessProperties";

        propertiesOntology = root + "ontologies_namespaces\\addedOntology.ttl";
        directoryPathCopie = "C:\\Users\\pc\\Desktop\\PFE\\Files\\DataLog\\test - Copie";
        writingFilePath = root + "ProgramOutput\\Fichier_log_Nettoye_Complet_Parallel_test.txt";
        writingFilePathCopie = root + "ProgramOutput\\Fichier_log_Nettoye_Complet_Parallel_Java_Copie.txt";
        modelsFilePath = root + "ProgramOutput\\Fichier_models.rdf";
        syntaxValidFileTest = root + "ProgramOutput\\Fichier_Syntaxe_Valide_Test.txt";

        /* Scénario 2 : Analytic Queries **/
        AggregQueriesFile = root + rootScenarioAnalytic + "ProgramOutput\\Fichier_Analytic_Queries.txt";
        AnalyticQueriesFile = root + rootScenarioAnalytic + "ProgramOutput\\Fichier_Analytic_Queries2.txt";
        AnalyticSelectQueriesFile = root + rootScenarioAnalytic + "ProgramOutput\\Fichier_Analytic_Select_Queries.txt";
        SelectQueriesAndResult = root + rootScenarioAnalytic + "ProgramOutput\\Fichier_Analytic_Select_Queries_Results.txt";

        /* Execution time of programs **/
        timesFilePath = root + "ProgramOutput\\Fichier_Temps.yaml";
        timesFilePathTest = root + "ProgramOutput\\Fichier_TempsTest.yaml";
        queriesNumberFilePath = root + "ProgramOutput\\Fichier_queriesNumber.yaml";
        queriesNumberFilePathTest = root + "ProgramOutput\\Fichier_queriesNumberTest.yaml";

    }

    public static String getEndpoint() {
        return endpoint;
    }


}