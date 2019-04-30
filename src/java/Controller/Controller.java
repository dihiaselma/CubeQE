package Controller;

import Services.MDfromLogQueries.Util.FileOperation;
import Services.MDfromLogQueries.Util.ModelUtil;
import Services.MDfromLogQueries.Util.TdbOperation;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.rdf.model.impl.ResourceImpl;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

import static Services.MDfromLogQueries.Declarations.Declarations.*;

@org.springframework.stereotype.Controller

public class Controller {

    private Map<String, Object> times = FileOperation.loadYamlFile(timesFilePathTest);
    private Map<String, Object> queriesNumbers = FileOperation.loadYamlFile(queriesNumberFilePathTest);


    @RequestMapping("/index")
    public String pageAccueil(Model model) {

        String error = "";

        model.addAttribute("error", error);
        return "index2";
    }

    @RequestMapping("/test")
    public String testParam(Model model, @RequestParam String q) {

        String error = "";

        System.out.println("**********  name : "+q);
        model.addAttribute("error", error);
        return "index2";
    }




    @RequestMapping("/beforeGraphs")
    public String beforeGraphs(Model model) {

        String error = "";

        model.addAttribute("error", error);
        return "beforeGraphs";
    }


    @RequestMapping("/cleaning")
    public String Cleaning(Model model) {

        String error = "";

        model.addAttribute("timesMap", times);

        model.addAttribute("queriesNumbersMap", queriesNumbers);

        model.addAttribute("error", error);
        return "cleaning";
    }

    @RequestMapping("/execution")
    public String executing(Model model) {

        String error = "";

        model.addAttribute("timesMap", times);

        model.addAttribute("queriesNumbersMap", queriesNumbers);

        model.addAttribute("error", error);
        return "execution";
    }

    @RequestMapping("/testTree")
    public String pageTree(Model model) {
        JSONArray jsonArray = new JSONArray();

        HashMap<String, org.apache.jena.rdf.model.Model> modelHashMap = TdbOperation.unpersistNumberOfModelsMap(TdbOperation.dataSetAnnotated, 3);

        Iterator<String> kies = modelHashMap.keySet().iterator();


        while (kies.hasNext()) {
            String key = kies.next();
            jsonArray.add(ModelUtil.modelToJSON(modelHashMap.get(key), key));
        }

        String error = "";
        System.out.println(jsonArray.toJSONString());


        model.addAttribute("models", jsonArray.toJSONString());
        model.addAttribute("error", error);
        return "treeView";
    }


    @RequestMapping("/subjectsBlocks")
    public String subjectsBlocks(Model model) {
        String error = "Error";

        JSONArray jsonArray = new JSONArray();
        JSONArray jsonArrayGlobal = new JSONArray();

        HashMap<String, org.apache.jena.rdf.model.Model> modelHashMap = TdbOperation.unpersistNumberOfModelsMap(TdbOperation.dataSetAnnotated, 20);

        Set<String> kies = modelHashMap.keySet();
        int i = 1;

        for (String key : kies) {
            JSONObject jsonObject = new JSONObject();

            Resource subject = new ResourceImpl (key);

            jsonObject.put("name", subject.getLocalName());

            jsonObject.put("fullName", key);
            jsonObject.put("value", 10);
            jsonArray.add(jsonObject);

            if (jsonArray.size() == 5) {

                JSONObject jsonChildren = new JSONObject();
                jsonChildren.put("children", jsonArray);
                jsonChildren.put("name", "subjects" + i);
                jsonArrayGlobal.add(jsonChildren);
                i++;
                jsonArray = new JSONArray();
            }

        }


        model.addAttribute("subjects", jsonArrayGlobal.toJSONString());
        model.addAttribute("error", error);
        return "subjectsBlocks";
    }


}
