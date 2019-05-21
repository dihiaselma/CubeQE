package Services.MDPatternDetection.AnalyticalQueriesClasses;

import Services.MDPatternDetection.AnnotationClasses.MDGraphAnnotated;
import Services.MDPatternDetection.ExecutionClasses.QueryExecutor;
import Services.MDPatternDetection.GraphConstructionClasses.QueryUpdate;
import Services.MDfromLogQueries.Declarations.Declarations;
import Services.MDfromLogQueries.Util.Constants2;
import Services.MDfromLogQueries.Util.FileOperation;
import Services.MDfromLogQueries.Util.TdbOperation;
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

import java.util.*;

public class AnalyticQueries {

    public static int queriesNumber=0;

    /* Returns if a query is analytic or not (if aggregator is count(*) it's not analytics ) */
    public static boolean isAnalytic(Query query) {
        List<ExprAggregator> exprAggregatorList;
        int i = 0;
        if (query.hasAggregators()) {
            exprAggregatorList = query.getAggregators();
            for (ExprAggregator exprAggregator : exprAggregatorList) {
                System.out.println("la var :" + exprAggregator.getAggregator().getExprList());
                // the exprList is null in case it's a count(*)
                if (exprAggregator.getAggregator().getExprList() != null) {
                    i++;
                }
            }
        }
        return (i > 0);
    }

    /* Returns a list of analytic Queries from an input list of queries */
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
        Query query;
        int size = queryList.size();
        QueryExecutor queryExecutor = new QueryExecutor();
        AnalyticQuery analyticQuery = new AnalyticQuery();
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
        BasicPattern bpConstruct = queryUpdate.getQueryConstruction().getBpConstruct(); // Getting construct BasicPattern to use it to construct the Graph Pattern
        List<Triple> bpWhereTriples = queryUpdate.getQueryConstruction().getBpWhere().getList();

        analyticQuery.selectQuery = query;
        analyticQuery.selectQuery.addResultVar(analyticQuery.getAggregVariable());

        ResultSet resultSet;
        resultSet = queryExecutor.executeQuerySelect(query, endpoint);

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
            if (querySolution.get(analyticQuery.aggregVariables).isResource()) {
                Node rdfNode = getRdfTypeVariable(bpWhereTriples, analyticQuery.aggregVariables);
                if (!rdfNode.isBlank())
                    subject = new ResourceImpl(rdfNode.getName());
                else
                    subject = querySolution.get(analyticQuery.aggregVariables).asResource();

                property = new PropertyImpl("http://0.0.0.0/lodlinc/countMeasure");
                object = new ResourceImpl("http://www.w3.org/2001/XMLSchema#integer");
                statement = new StatementImpl(subject, property, object);
                model.add(statement);
            }
            modelHashSet.add(model);
        }
        return modelHashSet;
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
    String aggregVariables;

    String getAggregVariable() {

        aggregVariables = selectQuery.getAggregators().get(0).getAggregator().getExprList().get(0).getVarName();
        return aggregVariables;
    }

}