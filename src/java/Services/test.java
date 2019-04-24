package Services;

import Services.MDfromLogQueries.Util.FileOperation;
import com.google.common.base.Stopwatch;

import java.util.HashMap;
import java.util.Set;

import static Services.MDfromLogQueries.Declarations.Declarations.queriesNumberFilePath;
import static Services.MDfromLogQueries.Declarations.Declarations.queriesNumberFilePathTest;
import static Services.MDfromLogQueries.Declarations.Declarations.timesFilePathTest;


public class test {

    public static void main(String args[]) {

        Stopwatch stopwatchSelect = Stopwatch.createStarted();
/*
        FileOperation.writeInYAMLFile(timesFilePathTest, "Log_Cleaning", 604 );
        FileOperation.writeInYAMLFile(timesFilePathTest, "Deduplication", 604 );
        FileOperation.writeInYAMLFile(timesFilePathTest, "Syntactical_Validation", 604 );
        FileOperation.writeInYAMLFile(timesFilePathTest, "Execution_QueriesnonExecuted", 604 );
        FileOperation.writeInYAMLFile(timesFilePathTest, "Consolidation_nbModels", 604 );
        FileOperation.writeInYAMLFile(timesFilePathTest, "Annotation", 604 );


        HashMap<String, Integer> map= (HashMap<String, Integer>) FileOperation.loadYamlFile(timesFilePathTest);
*/


        FileOperation.writeInYAMLFile(queriesNumberFilePathTest, "Log_Cleaning", 34567 );
        FileOperation.writeInYAMLFile(queriesNumberFilePathTest, "Deduplication", 34567 );
        FileOperation.writeInYAMLFile(queriesNumberFilePathTest, "Syntactical_Validation", 34567 );
        FileOperation.writeInYAMLFile(queriesNumberFilePathTest, "Execution_QueriesnonExecuted", 34567 );
        FileOperation.writeInYAMLFile(queriesNumberFilePathTest, "Consolidation_nbModels", 34567 );
        FileOperation.writeInYAMLFile(queriesNumberFilePathTest, "Annotation", 34567 );


        HashMap<String, Integer> map= (HashMap<String, Integer>) FileOperation.loadYamlFile(queriesNumberFilePathTest);




        Set<String> kies= map.keySet();

        for (String key:kies){

            System.out.println(key+" : "+map.get(key));

        }


        stopwatchSelect.stop();

        System.out.println(" Temps de transformation " + stopwatchSelect);


    }


}
