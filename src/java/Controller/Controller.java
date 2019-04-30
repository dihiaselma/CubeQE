package Controller;
import Services.MDfromLogQueries.Util.FileOperation;
import Services.MDfromLogQueries.Util.ModelUtil;
import Services.MDfromLogQueries.Util.TdbOperation;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

import static Services.MDfromLogQueries.Declarations.Declarations.*;

@org.springframework.stereotype.Controller

public class Controller {

    private Map<String, Object> times= FileOperation.loadYamlFile(timesFilePathTest);
    private Map<String, Object> queriesNumbers = FileOperation.loadYamlFile(queriesNumberFilePathTest);


    @RequestMapping("/index")
    public String pageAccueil(Model model){

        String error ="";

        model.addAttribute("error",error);
        return "index2";
    }

    @RequestMapping("/beforeGraphs")
    public String beforeGraphs(Model model){

        String error ="";

        model.addAttribute("error",error);
        return "subjectsBlocks";
    }

    @RequestMapping("/chooseScenario")
    public String chooseScnerio(Model model){

        String error ="";

        model.addAttribute("error",error);
        return "chooseScenario";
    }



    @RequestMapping("/cleaning")
    public String Cleaning(Model model){

        String error ="";

        model.addAttribute("timesMap", times);

        model.addAttribute("queriesNumbersMap", queriesNumbers);

        model.addAttribute("error",error);
        return "cleaning";
    }

    @RequestMapping("/execution")
    public String executing(Model model){

        String error ="";

        model.addAttribute("timesMap", times);

        model.addAttribute("queriesNumbersMap", queriesNumbers);

        model.addAttribute("error",error);
        return "execution";
    }

    @RequestMapping("/testTree")
    public String pageTree(Model model){
        Set<JSONObject> models = new HashSet<>();
        JSONArray jsonArray = new JSONArray();

        HashMap<String, org.apache.jena.rdf.model.Model> modelHashMap = TdbOperation.unpersistNumberOfModelsMap(TdbOperation.dataSetAnnotated,10);
        Iterator<String> kies = modelHashMap.keySet().iterator();
        while (kies.hasNext())
        {
            String key = kies.next();
            if (modelHashMap.get(key).size()<100)
                jsonArray.add(ModelUtil.modelToJSON(modelHashMap.get(key),key));
        }
        String erreur ="";
        System.out.println(jsonArray.toJSONString());
        model.addAttribute("models",jsonArray);
        model.addAttribute("erreur",erreur);
        return "MDGraph";
    }
    
    
    @RequestMapping("/subjectsBlocks")
    public String subjectsBlocks(Model model){
        String error ="Error";
        
        JSONArray jsonArray = new JSONArray();

        HashMap<String, org.apache.jena.rdf.model.Model> modelHashMap = TdbOperation.unpersistNumberOfModelsMap(TdbOperation.dataSetAnnotated,5);

        Set<String> kies = modelHashMap.keySet();

        for (String key:kies){
            JSONObject jsonObject = new JSONObject();



            jsonObject.put("name", key);
            jsonObject.put("value",1);
            jsonArray.add(jsonObject);

        }

        JSONObject jsonChildren = new JSONObject();
        jsonChildren.put("children", jsonArray);
        jsonChildren.put("name", "Subjects");
        System.out.println(jsonArray.toJSONString());



        model.addAttribute("subjects",jsonChildren.toJSONString());
        model.addAttribute("error", error);
        return "subjectsBlocks";
    }


}
