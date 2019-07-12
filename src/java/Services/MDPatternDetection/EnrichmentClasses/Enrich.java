package Services.MDPatternDetection.EnrichmentClasses;


import Services.MDPatternDetection.AnnotationClasses.Annotations;
import Services.MDPatternDetection.ConsolidationClasses.Consolidation;
import Services.MDPatternDetection.ExecutionClasses.QueryExecutor;
import Services.MDPatternDetection.ExecutionClasses.QueryExecutorScala;
import Services.MDfromLogQueries.Declarations.Declarations;
import Services.MDfromLogQueries.Util.*;
import Services.Statistics.StatisticsAnalytic;
import com.google.common.base.Stopwatch;
import org.apache.jena.graph.Node;
import org.apache.jena.graph.NodeFactory;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.*;
import org.apache.jena.rdf.model.impl.PropertyImpl;
import org.apache.jena.rdf.model.impl.ResourceImpl;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;
import org.apache.jena.vocabulary.XSD;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import static Services.MDfromLogQueries.Util.FileOperation.writeStatisticsListInFile2;

public class Enrich {

    public static String endpoint = "http://linkedgeodata.org/sparql";
    public static Constants2 constants2;
    public static ArrayList<StatisticsAnalytic> statisticsAnalytics4Fact = new ArrayList<>();
    public static ArrayList<StatisticsAnalytic> statisticsAnalytics4Dimension = new ArrayList<>();
    private static int nb_attribute = 0;
    private static int nb_objectProperty = 0;
    private static BasicProperties basicProperties = new BasicProperties();
    private static Datatype_Types datatype_types = new Datatype_Types();
    private static XSDMeasure_Types xsdMeasure_types = new XSDMeasure_Types();
    private static Property annotProperty = new PropertyImpl("http://loglinc.dz/annotated");


    public static  HashMap<String, Model> enrichMDSchema(HashMap<String, Model>  models) {

        int numModel = 0;
        Model model;
        Set<String> keyset = models.keySet();
        try {
            for (String key: keyset) {
                numModel++;
                System.out.println("model num: " + numModel);
                enrichModel(models.get(key));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return models;
    }


    public static void enrichModel(Model model) {
        try {
            ResIterator resIterator = model.listSubjects();
            int numNoeud = 0;

            while (resIterator.hasNext()) {
                Resource node = resIterator.next();
                nb_attribute = 0;
                nb_objectProperty = 0;
                numNoeud++;
                // System.out.println("le noeud num: "+numNoeud);
                enrichNode(node);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public static void enrichNode(Resource node) {

        if (node.hasProperty(annotProperty, Annotations.FACT.toString())) {
            System.out.println("je suis dans FACT");
            countOtherProperties(node,node, false);
            StatisticsAnalytic statisticsAnalytic = new StatisticsAnalytic();
            statisticsAnalytic.type = "Fact";
            statisticsAnalytic.URI = node.toString();
            statisticsAnalytic.nbDim += nb_objectProperty;
            statisticsAnalytic.nbFactAtt += nb_attribute;
            statisticsAnalytics4Fact.add(statisticsAnalytic);
        } else {
            if (node.hasProperty(annotProperty, Annotations.DIMENSION.toString())) {
                System.out.println("je suis dans DIMENSION");
                countOtherProperties(node,node, false);
                StatisticsAnalytic statisticsAnalytic = new StatisticsAnalytic();
                statisticsAnalytic.type = "Dimension";
                statisticsAnalytic.URI = node.toString();
                statisticsAnalytic.nbLevel += nb_objectProperty;
                statisticsAnalytic.nbDimAtt += nb_attribute;
                statisticsAnalytics4Dimension.add(statisticsAnalytic);
            }
        }
    }

    public static void countOtherProperties(Resource node, Resource subject, boolean finish) {
        ConstantsUtil constantsUtil = new ConstantsUtil();
        String queryStr = "SELECT DISTINCT ?p ?o ?otype WHERE { <" + node.getURI() + "> ?p ?o. Optional { ?o a ?otype.}} LIMIT 40";
        QueryExecutor queryExecutor = new QueryExecutor();
        QuerySolution querySolution;
        RDFNode predicate;
        RDFNode object;
        RDFNode objectType;
        RDFNode addedObject= null;
        System.out.println("count properties");
        try {
            Query query = QueryFactory.create(queryStr);
            //ResultSet results = queryExecutor.executeQuerySelect(query, endpoint);
            ResultSet results = QueryExecutorScala.executeQuerySelect(query,endpoint);

            if (results != null) {
            while (results.hasNext()) {
                querySolution = results.nextSolution();
                predicate = querySolution.get("p");
                object = querySolution.get("o");
                objectType = querySolution.get("otype");
                //System.out.print("*\t");
                if (!node.hasProperty(new PropertyImpl(predicate.asResource().getURI()), object)) {
                    if (!BasicProperties.properties.contains(predicate)) {
                        if (object.isLiteral() || Datatype_Types.types.contains(object.asResource())
                                || object.
                                asResource().
                                getNameSpace().
                                equals("http://www.w3.org/2001/XMLSchema")) {
                            addedObject = datatypePropertyTreatement(subject, predicate, objectType, constantsUtil);
                            //subject.addProperty(new PropertyImpl(predicate.asResource().getURI()), object);
                            nb_attribute++;
                        } else {

                            //   System.out.println(querySolution.get("?p").toString());
                            String propertyType;

                            try {
                                propertyType = constantsUtil.getPropertyType(new PropertyImpl(predicate.asResource().toString()));
                            } catch (Exception e) {

                                propertyType = constantsUtil.findPropertyEndpoint(predicate.asResource().toString(), endpoint);
                            }

                            switch (propertyType) {
                                case ("datatypeProperty"): {
                                    addedObject = datatypePropertyTreatement(subject, predicate, objectType, constantsUtil);
                                    nb_attribute++;
                                }
                                break;
                                case ("objectProperty"): {
                                    addedObject = objectPropertyTreatement(subject, predicate, object, objectType, constantsUtil);
                                    nb_objectProperty++;
                                }
                                break;
                                default: {
                                    if (object.isResource()) {
                                        addedObject = objectPropertyTreatement(subject, predicate, object, objectType, constantsUtil);
                                        nb_objectProperty++;
                                    } else if (object.isLiteral()) {
                                        addedObject = datatypePropertyTreatement(subject, predicate, objectType, constantsUtil);
                                        nb_attribute++;
                                    }
                                }
                            }

                        }
                        if (addedObject!=null)
                        subject.addProperty(new PropertyImpl(predicate.asResource().getURI()), addedObject);
                    } else if (predicate.equals(RDFS.subClassOf) && !finish) {
                        countOtherProperties(object.asResource(), subject, true);
                    }
                }
            }
        }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static RDFNode datatypePropertyTreatement(Resource subject, RDFNode predicate, RDFNode objectType, ConstantsUtil constantsUtil) {
        Node propertyRange;
        RDFNode object;

        if (objectType != null)
        {
            object = objectType;
        }
        else if ((propertyRange = constantsUtil.getRangeofProperty(new PropertyImpl(predicate.asResource().getURI())) )!= null) {

            object = new ResourceImpl(propertyRange.getURI());
        }
        else {
            object = RDFS.Literal;
        }
        return object;
    }

    private static RDFNode objectPropertyTreatement(Resource subject, RDFNode predicate, RDFNode object, RDFNode objectType, ConstantsUtil constantsUtil) {
        Node propertyRange;
        RDFNode returnObject;
        if (objectType != null)
            returnObject = objectType;
        else if ((propertyRange = constantsUtil.getRangeofProperty(new PropertyImpl(predicate.asResource().getURI())) )!= null )
        {
            returnObject = new ResourceImpl(propertyRange.getURI());
        }
        else {
            returnObject = null;
            //subject.addProperty(new PropertyImpl(predicate.asResource().getURI()),object);
        }
        return returnObject;
    }


    public static void main(String args[])  {
        new Declarations();
        Declarations.setEndpoint("DogFood");
        constants2 = new Constants2();
       // HashMap<String, Model> modelsAnnotated = TdbOperation.unpersistModelsMap(TdbOperation.dataSetAnnotated);
        HashMap<String, Model> modelsAnnotated = TdbOperation.unpersistNumberOfModelsMap(TdbOperation.dataSetAnnotated,5);
        //ArrayList<Model> models = new ArrayList<>(modelsAnnotated.values());
      //  ArrayList<Model> modelss = new ArrayList<>(models.subList(1, 20));
        Stopwatch stopwatch = Stopwatch.createUnstarted();
        stopwatch.start();

        System.out.println("*****************Before*********************");
        Consolidation.afficherListInformations(modelsAnnotated);
        HashMap<String, Model> modelsEnriched = enrichMDSchema(modelsAnnotated);

        System.out.println("******************After ***********************");

        Consolidation.afficherListInformations(modelsEnriched);

        writeStatisticsListInFile2(statisticsAnalytics4Fact, Declarations.paths.get("statisticsAnalyticFactFile"));
        writeStatisticsListInFile2(statisticsAnalytics4Dimension, Declarations.paths.get("statisticsAnalyticDimFile"));
        stopwatch.stop();
        System.out.println(" le temps ecoulé : " + stopwatch);

    }
}
