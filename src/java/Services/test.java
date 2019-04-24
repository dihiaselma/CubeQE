package Services;

import Services.MDfromLogQueries.Util.FileOperation;
import com.google.common.base.Stopwatch;

import java.util.HashMap;
import java.util.Set;
import static Services.MDfromLogQueries.Declarations.Declarations.queriesNumberFilePathTest;


public class test {

    public static void main(String args[]) {

        Stopwatch stopwatchSelect = Stopwatch.createStarted();
/*
        FileOperation.writeInYAMLFile(queriesNumberFilePathTest, "Log Cleaning", 23456 );
        FileOperation.writeInYAMLFile(queriesNumberFilePathTest, "Deduplication", 23456 );
        FileOperation.writeInYAMLFile(queriesNumberFilePathTest, "Syntactical_Validation", 23456 );
        FileOperation.writeInYAMLFile(queriesNumberFilePathTest, "Execution_QueriesnonExecuted", 23456 );
        FileOperation.writeInYAMLFile(queriesNumberFilePathTest, "Consolidation_nbModels", 23456 );
        FileOperation.writeInYAMLFile(queriesNumberFilePathTest, "Annotation", 23456 );
*/

        HashMap<String, Object> map= (HashMap<String, Object>) FileOperation.loadYamlFile(queriesNumberFilePathTest);

        Set<String> kies= map.keySet();

        for (String key:kies){

            System.out.println(key+" : "+map.get(key));

        }


        stopwatchSelect.stop();

        System.out.println(" Temps de transformation " + stopwatchSelect);


    }


}
