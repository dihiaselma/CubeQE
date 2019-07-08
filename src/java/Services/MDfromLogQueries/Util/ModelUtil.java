package Services.MDfromLogQueries.Util;

import Services.MDPatternDetection.AnnotationClasses.Annotations;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.impl.PropertyImpl;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;

public class ModelUtil {
    private static Property annotProperty = new PropertyImpl("http://loglinc.dz/annotated");

    public static JSONObject modelToJSON(Model model, String e_subject)
    {
        Set<Resource> visitedNodes = new HashSet<>();
        JSONObject jsonObject = new JSONObject();
        Resource subject  = model.getResource(e_subject);
        visitedNodes.add(subject);
        jsonObject.put("id",subject.getLocalName());
        jsonObject.put("color", "#713E8D");
        jsonObject.put("value",4);
        jsonObject.put("children",propertyIterate(subject,visitedNodes));

        return jsonObject;
    }

    public static JSONObject modelToJSON(Model model, String e_subject, int limit)
    {
        Set<Resource> visitedNodes = new HashSet<>();
        JSONObject jsonObject = new JSONObject();
        Resource subject  = model.getResource(e_subject);
        visitedNodes.add(subject);
        jsonObject.put("id",subject.getLocalName());
        jsonObject.put("color", "#713E8D");
        jsonObject.put("value",4);
        jsonObject.put("children",propertyIterate(subject,visitedNodes, 0));

        return jsonObject;
    }


    public static JSONObject subjectToJSON( String e_subject)
    {
        JSONObject jsonObject = new JSONObject();


        jsonObject.put("name", e_subject);
       return jsonObject;
    }



    public static JSONArray propertyIterate(Resource subject,Set<Resource> visitedNodes)
    {
        List<Statement> propertyIterator = subject.listProperties().toList();
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        for (Statement stat : propertyIterator) {
            jsonObject = new JSONObject();
            if (stat.getPredicate().equals(annotProperty))
            {
                jsonObject.put("id",stat.getObject().toString());
                jsonObject.put("color","#A5ABEE");
                jsonObject.put("value",1);
            }

            if (stat.getObject().isResource() && stat.getObject().asResource().listProperties().hasNext() && !visitedNodes.contains(stat.getObject().asResource())) {
                if (stat.getObject().asResource().hasProperty(annotProperty, Annotations.DIMENSION.toString()) ||
                        stat.getObject().asResource().hasProperty(annotProperty, Annotations.DIMENSIONLEVEL.toString()))
                {
                    jsonObject.put("name",stat.getPredicate().getLocalName());
                    jsonObject.put("id",stat.getObject().asResource().getLocalName());
                    visitedNodes.add(subject);
                    jsonObject.put("color","#6A6DDE");
                    jsonObject.put("value",3);
                }
                else {
                    jsonObject.put("name",stat.getObject().asResource().getLocalName());
                    jsonObject.put("id",stat.getPredicate().getLocalName());
                    jsonObject.put("color","#EB6EB0");
                    jsonObject.put("value",2);
                }
                jsonObject.put("children", propertyIterate(stat.getObject().asResource(), visitedNodes));
            }
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
    public static JSONArray propertyIterate(Resource subject,Set<Resource> visitedNodes,int level)
    {
        List<Statement> propertyIterator = subject.listProperties().toList();
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        level++;
        if(level<4) {
            for (Statement stat : propertyIterator) {
                jsonObject = new JSONObject();
                if (stat.getPredicate().equals(annotProperty)) {
                    jsonObject.put("id", stat.getObject().toString());
                    jsonObject.put("color", "#A5ABEE");
                    jsonObject.put("value", 1);
                }

                if (stat.getObject().isResource() && stat.getObject().asResource().listProperties().hasNext() && !visitedNodes.contains(stat.getObject().asResource())) {
                    if (stat.getObject().asResource().hasProperty(annotProperty, Annotations.DIMENSION.toString()) ||
                            stat.getObject().asResource().hasProperty(annotProperty, Annotations.DIMENSIONLEVEL.toString())) {

                        jsonObject.put("name", stat.getPredicate().getLocalName());
                        jsonObject.put("id", stat.getObject().asResource().getLocalName());
                        visitedNodes.add(subject);
                        jsonObject.put("color", "#6A6DDE");
                        jsonObject.put("value", 3);
                    } else {
                        jsonObject.put("name", stat.getObject().asResource().getLocalName());
                        jsonObject.put("id", stat.getPredicate().getLocalName());
                        jsonObject.put("color", "#EB6EB0");
                        jsonObject.put("value", 2);
                    }
                    jsonObject.put("children", propertyIterate(stat.getObject().asResource(), visitedNodes));
                }
                jsonArray.add(jsonObject);
            }
        }
        else {
            for (Statement stat : propertyIterator) {
                if (stat.getPredicate().equals(annotProperty)) {
                    jsonObject = new JSONObject();
                    jsonObject.put("id", stat.getObject().toString());
                    jsonObject.put("color", "#A5ABEE");
                    jsonObject.put("value", 1);
                    jsonObject.put("name", stat.getPredicate().getLocalName());
                    jsonArray.add(jsonObject);
                }
            }
        }
        return jsonArray;
    }

    public static String testJson()
    {
        JSONArray jsonArray = new JSONArray();

        HashMap<String, Model> modelHashMap = TdbOperation.unpersistNumberOfModelsMap(TdbOperation.dataSetAnnotated,2);
        Iterator<String> kies = modelHashMap.keySet().iterator();
        while (kies.hasNext())
        {
            String key = kies.next();
            //  ModelUtil.modelToJSON(modelHashMap.get(key),key);
            //models.add();
            jsonArray.add(ModelUtil.modelToJSON(modelHashMap.get(key),key));
        }
        return jsonArray.toJSONString();
    }

    public static Double averageSize(HashMap<String,Model> modelHashMap){
        Double average = 0.0;
        int nbModels = modelHashMap.size();
        Set<String> keySet = modelHashMap.keySet();
        for (String key : keySet)
        {
            average += modelHashMap.get(key).size();
        }
        average = average/nbModels;
        return average;
    }
}
