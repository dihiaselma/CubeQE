package Services;

import Services.MDfromLogQueries.Declarations.Declarations;
import Services.MDfromLogQueries.Util.FileOperation;
import com.google.common.base.Stopwatch;

import javax.naming.event.ObjectChangeListener;
import java.util.HashMap;



public class test {

    public static void main(String args[]) {

        Stopwatch stopwatchSelect = Stopwatch.createStarted();

        FileOperation.writeInYAMLFile(Declarations.paths.get("timesFilePathTest"), "Log_Cleaning", 10*60 );
        FileOperation.writeInYAMLFile(Declarations.paths.get("timesFilePathTest"), "Deduplication", 60 );
        FileOperation.writeInYAMLFile(Declarations.paths.get("timesFilePathTest"), "Syntactical_Validation", 3*60 );
        FileOperation.writeInYAMLFile(Declarations.paths.get("timesFilePathTest"), "ConstructMSGraphs", 16*60 );
        FileOperation.writeInYAMLFile(Declarations.paths.get("timesFilePathTest"), "Execution", 5*24*60*60 );
        FileOperation.writeInYAMLFile(Declarations.paths.get("timesFilePathTest"), "Consolidation", (32+77)*60 );
        FileOperation.writeInYAMLFile(Declarations.paths.get("timesFilePathTest"), "Annotation", (33+60)*60 );
        FileOperation.writeInYAMLFile(Declarations.paths.get("timesFilePathTest"), "Statistics", (2*60+20)*60 );


        HashMap<String, Object> map= (HashMap<String, Object>) FileOperation.loadYamlFile(Declarations.paths.get("timesFilePathTest"));


        FileOperation.writeInYAMLFile(Declarations.paths.get("queriesNumberFilePathTest"), "Log_Cleaning_nbLines", 3286774 );
        FileOperation.writeInYAMLFile(Declarations.paths.get("queriesNumberFilePathTest"), "Log_Cleaning", 3193672);
        FileOperation.writeInYAMLFile(Declarations.paths.get("queriesNumberFilePathTest"), "Deduplication", 1358987 );
        FileOperation.writeInYAMLFile(Declarations.paths.get("queriesNumberFilePathTest"), "Syntactical_Validation", 1358987-7705 );
        FileOperation.writeInYAMLFile(Declarations.paths.get("queriesNumberFilePathTest"), "ConstructMSGraphs_nbQueriesConstructed", 1311286 );
        FileOperation.writeInYAMLFile(Declarations.paths.get("queriesNumberFilePathTest"), "ConstructMSGraphs_nbQueriesNonConstructed", 39999 );
        FileOperation.writeInYAMLFile(Declarations.paths.get("queriesNumberFilePathTest"), "Execution_nbQueriesExecuted", 1200000 );
        FileOperation.writeInYAMLFile(Declarations.paths.get("queriesNumberFilePathTest"), "Execution_nbQueriesNonExecuted", 318615 );
        FileOperation.writeInYAMLFile(Declarations.paths.get("queriesNumberFilePathTest"), "Consolidation_nbModelsNonConsolidated", 34117400 );
        FileOperation.writeInYAMLFile(Declarations.paths.get("queriesNumberFilePathTest"), "Consolidation_nbModels", 197377 );


        HashMap<String, Object> map2= (HashMap<String, Object>) FileOperation.loadYamlFile(Declarations.paths.get("queriesNumberFilePathTest"));



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
