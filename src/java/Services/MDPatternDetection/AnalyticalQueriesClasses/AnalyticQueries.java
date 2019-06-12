package Services.MDPatternDetection.AnalyticalQueriesClasses;

import Services.MDPatternDetection.AnnotationClasses.MDGraphAnnotated;
import Services.MDPatternDetection.ConsolidationClasses.Consolidation;
import Services.MDPatternDetection.ExecutionClasses.QueryExecutor;
import Services.MDPatternDetection.GraphConstructionClasses.QueryUpdate;
import Services.MDfromLogQueries.Declarations.Declarations;
import Services.MDfromLogQueries.Util.Constants2;
import Services.MDfromLogQueries.Util.FileOperation;
import Services.MDfromLogQueries.Util.TdbOperation;
import org.apache.commons.math3.analysis.function.Exp;
import org.apache.jena.graph.Node;
import org.apache.jena.graph.NodeFactory;
import org.apache.jena.graph.Node_Variable;
import org.apache.jena.graph.Triple;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.*;
import org.apache.jena.rdf.model.impl.PropertyImpl;
import org.apache.jena.rdf.model.impl.ResourceImpl;
import org.apache.jena.rdf.model.impl.StatementImpl;
import org.apache.jena.sparql.core.BasicPattern;
import org.apache.jena.sparql.expr.ExprAggregator;
import org.apache.jena.vocabulary.RDF;
import org.rdfhdt.hdt.iterator.utils.Iter;

import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Pattern;

public class AnalyticQueries {

    public static int queriesNumber=0;

    /* Returns if a query is analytic or not (if aggregator is count(*) it's not analytics ) */
    public static boolean isAnalytic(Query query) {
        List<ExprAggregator> exprAggregatorList;
        int i = 0;
        if (query.hasAggregators()) {
            exprAggregatorList = query.getAggregators();
            for (ExprAggregator exprAggregator : exprAggregatorList) {
              //  System.out.println("la var :" + exprAggregator.getAggregator().getExprList());
                // the exprList is null in case it's a count(*)
                if (exprAggregator.getAggregator().getExprList() != null) {
                    i++;
                }
            }
        }
        return (i > 0);
    }

    /** Returns a list of analytic Queries from an input list of queries */
    public static ArrayList<String> getAnalyticQueries(ArrayList<String> queryList) {
        ArrayList<String> analyticQueriesList = new ArrayList<>();
        int nb_line = 0; //for statistical needs
        String queryStr;
        Query query;
        int size = queryList.size();
        while (nb_line < size) {
            try {
                nb_line++;
                queryStr = queryList.get(nb_line);
                query = QueryFactory.create(queryStr);
                if (query.hasAggregators() && isAnalytic(query)) {
                    analyticQueriesList.add(query.toString());
                    queriesNumber++;
                }
                System.out.println("line \t" + nb_line);
            } catch (Exception e) {
                // e.printStackTrace();
                System.out.println("erreur");
            }
        }
        return analyticQueriesList;
    }

    /* Execution of analytic queries and construction of resulting models */
    public static HashSet<Model> executeAnalyticQueriesList(ArrayList<String> queryList,String endpoint) {
        int nb_line = 0; //for statistical needs
        int nb = 0;
        String queryStr;
        HashSet<Model> modelHashSet = new HashSet<>();

        int size = queryList.size();

        while (nb_line < size) {
            try {
                nb_line++;
                //queryStr = line;
                System.out.println("requete num : " + nb_line);
                queryStr = queryList.get(nb_line);
                modelHashSet.addAll(executeAnalyticQuery(queryStr,endpoint));
                System.out.println("line \t" + nb_line);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("erreur");
                nb++;
            }
        }
        return modelHashSet;
    }

    public static HashSet<Model> executeAnalyticQuery(String queryStr,String endpoint) {

        QueryExecutor queryExecutor = new QueryExecutor();
        HashSet<Model> modelHashSet = new HashSet<>();
        AnalyticQuery analyticQuery = new AnalyticQuery();

        Query query = QueryFactory.create(queryStr);
        QueryUpdate queryUpdate = new QueryUpdate(query);// Adding missing rdf:type statements to the query
        query = queryUpdate.addAddedVariablesToResultVars(query); // Adding new variables to the resulting vars

        // Getting construct BasicPattern to use it to construct the Graph Pattern
        BasicPattern bpConstruct = queryUpdate.getQueryConstruction().getBpConstruct();

        System.out.println(" \nbp construct " +bpConstruct);

        List<Triple> bpWhereTriples = queryUpdate.getQueryConstruction().getBpWhere().getList();
        System.out.println(" \nbpWhere "+bpWhereTriples);

        analyticQuery.selectQuery = query;

        for (String var : analyticQuery.getAggregVariable().values()){
            analyticQuery.selectQuery.addResultVar(var);
        }


        ResultSet resultSet;

        //System.out.println(" je suis avant l'execution ");


        System.out.println("** query to execute  "+query);

        resultSet = queryExecutor.executeQuerySelect(query, endpoint);


        if (resultSet==null) System.out.println("null ");
     //   if (!resultSet.hasNext()) System.out.println("empty");

        modelHashSet.addAll(constructModels(resultSet, bpConstruct, analyticQuery, bpWhereTriples));
        return modelHashSet;
    }



        /* Construct models from Query Solutions after execution */
    public static HashSet<Model> constructModels(ResultSet resultSet, BasicPattern bpConstruct, AnalyticQuery analyticQuery
            , List<Triple> bpWhereTriples) {
        List<Triple> tripleList;
        HashSet<Model> modelHashSet = new HashSet<>();
        Model model;
        Node node;
        QuerySolution querySolution;
        Statement statement;
        Resource subject;
        Property property;
        RDFNode object;
       if (resultSet!= null) {

            while (resultSet.hasNext()) {


                model = ModelFactory.createDefaultModel();
                querySolution = resultSet.next();
                tripleList = bpConstruct.getList();

                for (Triple triple : tripleList) {
                    node = triple.getSubject();
                    if (node.isVariable()) {
                        subject = querySolution.get(node.getName()).asResource();
                    } else {
                        subject = new ResourceImpl(node.getURI());

                    }
                    node = triple.getPredicate();
                    if (node.isVariable()) {
                        property = new PropertyImpl(querySolution.get(node.getName()).asNode().getURI());
                    } else {
                        property = new PropertyImpl(node.getURI());

                    }
                    node = triple.getObject();
                    if (node.isVariable()) {
                        object = new ResourceImpl(querySolution.get(node.getName()).asNode().getURI());
                    } else {
                        object = new ResourceImpl(node.getURI());

                    }
                    statement = new StatementImpl(subject, property, object);

                    if (!model.contains(statement))
                        model.add(statement);
                }


                addAgregationMeasures(model, querySolution, analyticQuery, bpWhereTriples);

                modelHashSet.add(model);
            }
        }
     //   Consolidation.afficherListInformationsSet(modelHashSet);
        return modelHashSet;
    }



    private static void addAgregationMeasures (Model model,QuerySolution querySolution, AnalyticQuery analyticQuery,List<Triple> bpWhereTriples){
        Statement statement;
        Resource subject;
        Property property;
        RDFNode object;


        int size= analyticQuery.getAggregVariable().keySet().size();
        Iterator it= analyticQuery.getAggregVariable().entrySet().iterator();

       for (int i=0; i<size; i++) {

           Map.Entry<String, String> aggregartor_var= (Map.Entry)  it.next();

           if (querySolution.get(aggregartor_var.getValue()).isResource()) {

               Node rdfNode = getRdfTypeVariable(bpWhereTriples, aggregartor_var.getValue());

               //subject
               if (!rdfNode.isBlank())
                   subject = new ResourceImpl(rdfNode.getName());
               else
                   subject = querySolution.get( aggregartor_var.getValue()).asResource();

               //property
               if (Pattern.compile(Pattern.quote("COUNT"), Pattern.CASE_INSENSITIVE)
                       .matcher(aggregartor_var.getKey()).find())
               {
                   property = new PropertyImpl("http://0.0.0.0/loglinc/numberOf");

               }else
               {
                   property = new PropertyImpl("http://0.0.0.0/loglinc/"+aggregartor_var.getKey());
               }

               //object
               object = new ResourceImpl("http://www.w3.org/2001/XMLSchema#integer");

               statement = new StatementImpl(subject, property, object);

               model.add(statement);
           }
       }

    }





    /* Returns the rdf:type variable of an instance variable given in input */
    private static Node getRdfTypeVariable(List<Triple> bpWhereTriples, String variable) {
        Node rdfTypeVar = NodeFactory.createBlankNode();

        for (Triple triple : bpWhereTriples) {
            if (triple.subjectMatches(new Node_Variable(variable)) && triple.predicateMatches(RDF.type.asNode())) {
                rdfTypeVar = triple.getObject();
                return rdfTypeVar;
            }
        }
        return rdfTypeVar;
    }

    public static void AnalyticQueriesProcessing()
    {
        ArrayList<String> analyticQueriesList;
        new Constants2();
        new TdbOperation();
        String endpoint = "https://dbpedia.org/sparql";
        ArrayList<String> queryList;
        queryList = (ArrayList<String>) FileOperation.ReadFile(Declarations.paths.get("syntaxValidFile2"));
        analyticQueriesList = getAnalyticQueries(queryList);
        FileOperation.WriteInFile(Declarations.paths.get("AnalyticQueriesFile"), analyticQueriesList);
        HashSet<Model> modelHashSet = executeAnalyticQueriesList(queryList,endpoint);
        TdbOperation.persistNonNamedModels(modelHashSet, TdbOperation.dataSetAnalytic);

    }

    public static void AnalyticQueriesAnnotation()
    {
        ArrayList<String> analyticQueriesList;
        new Constants2();
        new TdbOperation();

        HashMap<String, Model> modelHashMap = TdbOperation.unpersistModelsMap(TdbOperation.dataSetAnalytic);
        HashMap<String,Model> modelHashMapAnnotated = new HashMap<>();
        if (modelHashMap != null) {
            modelHashMapAnnotated  = MDGraphAnnotated.constructMDGraphs(modelHashMap);
        }
        TdbOperation.persistHashMap(modelHashMapAnnotated,TdbOperation.dataSetAnalyticAnnotated);
    }



    public static void main(String args[]) {
        HashMap<String, Model> modelHashMapAnnotated = TdbOperation.unpersistModelsMap(TdbOperation.dataSetAnalyticAnnotated);

        Set<String> keys = modelHashMapAnnotated.keySet();
        Model model;
        for (String key : keys) {
            model = modelHashMapAnnotated.get(key);
            System.out.println("*************************" + key + "*********************");
           // ConsolidationTest.afficherModel(model);
        }

    }



}

class AnalyticQuery {
    Query selectQuery;

     private  HashMap<String, String> aggregatorsMap= new HashMap<>();


    HashMap<String, String> getAggregVariable() {

        int size= selectQuery.getAggregators().size();
        for (int i=0; i<size;i++){

            aggregatorsMap.put(selectQuery.getAggregators().get(i).getAggregator().getName(),
                    selectQuery.getAggregators().get(i).getAggregator().getExprList().get(0).getVarName() );

        }

        return aggregatorsMap;
    }

}