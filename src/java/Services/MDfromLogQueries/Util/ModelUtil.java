package Services.MDfromLogQueries.Util;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;

public class ModelUtil {

    public static JSONObject modelToJSON(Model model, String e_subject)
    {
        Set<Resource> visitedNodes = new HashSet<>();
        JSONObject jsonObject = new JSONObject();
        Resource subject  = model.getResource(e_subject);
        visitedNodes.add(subject);
        jsonObject.put("id",subject.getURI());
        jsonObject.put("children",propertyIterate(subject,visitedNodes));

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
        //visitedNodes.add(subject);
        for (Statement stat : propertyIterator) {
            jsonObject = new JSONObject();
            jsonObject.put("id",stat.getObject().toString());
            jsonObject.put("name",stat.getPredicate().getURI());
            jsonObject.put("value",5);
            if (stat.getObject().isResource() && stat.getObject().asResource().listProperties().hasNext() && !visitedNodes.contains(stat.getObject().asResource()))
                jsonObject.put("children",propertyIterate(stat.getObject().asResource(),visitedNodes));
            jsonArray.add(jsonObject);
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
}
