package Services;

import Services.MDPatternDetection.AnalyticalQueriesClasses.AnalyticQueries;
import Services.MDPatternDetection.ConsolidationClasses.Consolidation;
import Services.MDPatternDetection.ConsolidationClasses.ConsolidationParallel;
import Services.MDPatternDetection.ExecutionClasses.QueryExecutor;
import Services.MDPatternDetection.GraphConstructionClasses.QueryUpdate;
import Services.MDfromLogQueries.Declarations.Declarations;
import Services.MDfromLogQueries.Util.TdbOperation;
import com.github.jsonldjava.shaded.com.google.common.base.Stopwatch;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.tdb.TDBFactory;
import scala.Array;
import scala.collection.JavaConverters;
import scala.concurrent.JavaConversions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

//TODO delete this class

public class AppTest {


    public static void main(String[] args) {

        Stopwatch stopwatch_total = Stopwatch.createStarted();

      //  TdbOperation.unpersistModelsMap(TdbOperation.originalDataSet);
   //
        String endpoint="dbPedia";
        String endpointUrl="http://www.scholarlydata.org/sparql/";

        Declarations.setEndpoint(endpoint);
        System.out.println(Declarations.paths.get("dataSetAlleviated20"));

        Dataset dataset = TDBFactory.createDataset(Declarations.paths.get("dataSetAlleviated20"));
        Iterator<String> it = dataset.listNames();

        int nb_models=0;
        try {

            while (it.hasNext()) {

                it.next();
               if (dataset.getNamedModel(it.next()).size()<20)
               {
                   nb_models++;
               }
              //  nb_models++;
            }


    }catch(Exception e){

        }


        System.out.println("nb_moels"+nb_models);
    }
}








