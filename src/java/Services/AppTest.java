package Services;

import Services.MDPatternDetection.AnalyticalQueriesClasses.AnalyticQueries;
import Services.MDPatternDetection.ConsolidationClasses.Consolidation;
import Services.MDPatternDetection.ConsolidationClasses.ConsolidationParallel;
import Services.MDPatternDetection.ExecutionClasses.QueryExecutor;
import Services.MDPatternDetection.GraphConstructionClasses.QueryUpdate;
import com.github.jsonldjava.shaded.com.google.common.base.Stopwatch;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.rdf.model.Model;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

//TODO delete this class

public class AppTest {


    public static void main(String[] args) {

        Stopwatch stopwatch_total = Stopwatch.createStarted();

        String querytest ="PREFIX  rdf:  <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  SELECT  (MIN(?val) AS ?min) (MAX(?val) AS ?max)   WHERE  { ?X0  rdf:type              <http://data.semanticweb.org/ns/swc/ontology#SocialEvent> ;  <http://www.w3.org/2002/12/cal/icaltzd#dtend>  ?val  } GROUP BY ?val";
        String querytest2 ="PREFIX pf:   <http://jena.hpl.hp.com/ARQ/property#>\n" +
                "SELECT DISTINCT ?uri\t\n" +
                "WHERE\t { ?uri  a <http://dbpedia.org/ontology/Person> ;\t                                  <http://www.w3.org/2000/01/rdf-schema#label>  ?label\t    \n" +
                "FILTER regex(str(?label), \"Jimi\", \"smi\")\t \n" +
                " } GROUP BY ?uri\n" +
                "ORDER BY DESC (COUNT(?uri)) LIMIT   3\n";

        String querytest3= "SELECT  (COUNT(?s) AS ?no)\tWHERE\t  { ?s  <http://www.w3.org/2000/01/rdf-schema#domain>  ?o }\tGROUP BY ?s";



        Query query = QueryFactory.create(querytest3);

        // System.out.println("== before ==\n" + query);

        QueryUpdate queryUpdate = new QueryUpdate(query);
        QueryExecutor queryExecutor = new QueryExecutor();

       //  queryExecutor.executeQuerySelect(query, endpoint);


        Query  constructedQuery = queryUpdate.toConstruct(query);


       // System.out.println(constructedQuery);

        HashSet<Model> models= AnalyticQueries.executeAnalyticQuery(querytest3, "http://www.scholarlydata.org/sparql/");
        ArrayList< Model> stringModelHashMap=new ArrayList<>();

        System.out.println(models.size());

           Consolidation.afficherListInformationsSet(models);

/*            stringModelHashMap.addAll(models);
        System.out.println(stringModelHashMap.size());

        HashMap<String, Model> hashMap=Consolidation.toStringModelsHashmap2 (stringModelHashMap);

        Consolidation.afficherListInformations(hashMap);
*/
        }


    }








