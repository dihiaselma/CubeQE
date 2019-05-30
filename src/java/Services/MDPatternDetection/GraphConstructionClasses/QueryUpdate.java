package Services.MDPatternDetection.GraphConstructionClasses;

import Services.MDPatternDetection.AnalyticalQueriesClasses.AnalyticQueries;
import Services.MDfromLogQueries.Declarations.Declarations;
import Services.MDfromLogQueries.Util.Constants2;
import org.apache.jena.graph.Node;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.rdf.model.impl.PropertyImpl;
import org.apache.jena.sparql.syntax.Template;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class QueryUpdate {

    /**
     * This class update queries with rdf:type triples
     **/

    private QueryConstruction queryConstruction = new QueryConstruction();


    public QueryUpdate() {
    }

    public QueryUpdate(Query query)
    {
        addGP2Query(query);

    }

    public static void main(String[] args) {

        Declarations.setEndpoint("DogFood");



       // new Constants2(); // init the constants tu use it next
        final String queryString = "PREFIX owl: <http://www.w3.org/2002/07/owl#>" +
                "PREFIX foaf: <http://xmlns.com/foaf/0.1/>" +
                "PREFIX dbo: <http://dbpedia.org/ontology/>" +
                "SELECT ?title WHERE {" +
                "     ?game a dbo:Game  ." +
                "?game foaf:friend ?op ." +
                "Filter (?game = \"gg\")" +
                "    OPTIONAL { ?game foaf:name ?title }." +
                "} ORDER by ?title limit 10";


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


        AnalyticQueries.executeAnalyticQuery("", "");


        //System.out.println("after :\n"+constructedQuery);

/*
        System.out.println(constructedQuery.getAggregators().get(0));
        System.out.println(constructedQuery.getAggregators().get(1));

        if (Pattern.compile(Pattern.quote("COUNT"), Pattern.CASE_INSENSITIVE)
                .matcher(constructedQuery.getAggregators().get(0).toString()).find())

        {
            System.out.println("http://0.0.0.0/loglinc/numberOf");

        }else
        {
            System.out.println("http://0.0.0.0/loglinc/"+constructedQuery.getAggregators().get(0).getAggregator().toString());
            System.out.println( constructedQuery.getAggregators().get(0).getAggregator().getName());

        }
*/
    }

    public QueryConstruction getQueryConstruction() {
        return queryConstruction;
    }

    public Query toConstruct(Query query) {
        Template constructTemplate = new Template(queryConstruction.getBpConstruct());
        query.setQueryConstructType();
        query.setConstructTemplate(constructTemplate);
        return query;
    }

    public Query addAddedVariablesToResultVars(Query query) {
        ArrayList<Node> nodeArrayList = queryConstruction.getAddedVariables();
        for (Node n : nodeArrayList) {
            try {
                query.addResultVar(n);
                query.addGroupBy(n);
            } catch (Exception e) {
                //e.printStackTrace();
//                System.out.println("not a variable");
            }
        }
        return query;
    }

    private Query addGP2Query(Query query) {
        QueryModifyElementVisitor qmev = new QueryModifyElementVisitor();
        qmev.walker(query.getQueryPattern(),queryConstruction);
        return query;
    }


}


