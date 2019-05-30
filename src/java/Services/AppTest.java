package Services;

import Services.MDPatternDetection.AnalyticalQueriesClasses.AnalyticQueries;
import Services.MDPatternDetection.GraphConstructionClasses.QueryUpdate;
import com.github.jsonldjava.shaded.com.google.common.base.Stopwatch;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

//TODO delete this class

public class AppTest {


    public static void main(String[] args) {

        Stopwatch stopwatch_total = Stopwatch.createStarted();

        String querytest ="PREFIX  rdf:  <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\t\t\t\n" +
                "\n" +
                "SELECT  (MIN(?val) AS ?min) (MAX(?val) AS ?max)\t\n" +
                "WHERE\t  { ?X0  rdf:type              <http://data.semanticweb.org/ns/swc/ontology#SocialEvent> ;\t" +
                "         <http://www.w3.org/2002/12/cal/icaltzd#dtend>  ?val\t  }\t\n" +
                "GROUP BY ?val\t";

        Query query = QueryFactory.create(querytest);

        // System.out.println("== before ==\n" + query);

        QueryUpdate queryUpdate = new QueryUpdate(query);

        Query  constructedQuery = queryUpdate.toConstruct(query);


        AnalyticQueries.executeAnalyticQuery(querytest, "http://www.scholarlydata.org/sparql/");





        }


    }








