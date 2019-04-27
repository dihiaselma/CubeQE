package Services;

import Services.MDfromLogQueries.Util.FileOperation;
import com.google.common.base.Stopwatch;

import javax.naming.event.ObjectChangeListener;
import java.util.HashMap;
import java.util.Set;

import static Services.MDfromLogQueries.Declarations.Declarations.queriesNumberFilePath;
import static Services.MDfromLogQueries.Declarations.Declarations.queriesNumberFilePathTest;
import static Services.MDfromLogQueries.Declarations.Declarations.timesFilePathTest;


public class test {

    public static void main(String args[]) {

        Stopwatch stopwatchSelect = Stopwatch.createStarted();

        FileOperation.writeInYAMLFile(timesFilePathTest, "Log_Cleaning", 10*60 );
        FileOperation.writeInYAMLFile(timesFilePathTest, "Deduplication", 60 );
        FileOperation.writeInYAMLFile(timesFilePathTest, "Syntactical_Validation", 3*60 );
        FileOperation.writeInYAMLFile(timesFilePathTest, "ConstructMSGraphs", 16*60 );
        FileOperation.writeInYAMLFile(timesFilePathTest, "Execution", 5*24*60*60 );
        FileOperation.writeInYAMLFile(timesFilePathTest, "Consolidation", (32+77)*60 );
        FileOperation.writeInYAMLFile(timesFilePathTest, "Annotation", (33+60)*60 );
        FileOperation.writeInYAMLFile(timesFilePathTest, "Statistics", (2*60+20)*60 );


        HashMap<String, Object> map= (HashMap<String, Object>) FileOperation.loadYamlFile(timesFilePathTest);


        FileOperation.writeInYAMLFile(queriesNumberFilePathTest, "Log_Cleaning_nbLines", 3286774 );
        FileOperation.writeInYAMLFile(queriesNumberFilePathTest, "Log_Cleaning", 3193672);
        FileOperation.writeInYAMLFile(queriesNumberFilePathTest, "Deduplication", 1358987 );
        FileOperation.writeInYAMLFile(queriesNumberFilePathTest, "Syntactical_Validation", 1358987-7705 );
        FileOperation.writeInYAMLFile(queriesNumberFilePathTest, "ConstructMSGraphs_nbQueriesConstructed", 1311286 );
        FileOperation.writeInYAMLFile(queriesNumberFilePathTest, "ConstructMSGraphs_nbQueriesNonConstructed", 39999 );
        FileOperation.writeInYAMLFile(queriesNumberFilePathTest, "Execution_nbQueriesExecuted", 1200000 );
        FileOperation.writeInYAMLFile(queriesNumberFilePathTest, "Execution_nbQueriesNonExecuted", 318615 );
        FileOperation.writeInYAMLFile(queriesNumberFilePathTest, "Consolidation_nbModelsNonConsolidated", 34117400 );
        FileOperation.writeInYAMLFile(queriesNumberFilePathTest, "Consolidation_nbModels", 197377 );


        HashMap<String, Object> map2= (HashMap<String, Object>) FileOperation.loadYamlFile(queriesNumberFilePathTest);



/*
        Set<String> kies= map.keySet();

        for (String key:kies){

            System.out.println(key+" : "+map.get(key));

        }

*/
        stopwatchSelect.stop();

        System.out.println(" Temps de transformation " + stopwatchSelect);


    }


}
