package Services;



import Services.MDPatternDetection.Alleviation.MDGraphsAlleviation;
import Services.MDfromLogQueries.Util.TdbOperation;
import com.google.common.base.Stopwatch;
import org.apache.jena.rdf.model.Model;

import java.util.HashMap;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
//TODO delete this class

public class AppTest {


    public static void main(String[] args) {


        Stopwatch stopwatch_total = Stopwatch.createStarted();


        HashMap<String, Model> results = TdbOperation.unpersistModelsMap(TdbOperation.dataSetConsolidate);


        System.out.println(" size of results dans le test "+results.size());
        MDGraphsAlleviation.MDGraphsAlleviate(results);


        stopwatch_total.stop();
        System.out.println("\nTime elapsed for the whole program is \t" + stopwatch_total.elapsed(MILLISECONDS));



    }




}






