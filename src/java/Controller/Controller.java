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

    @RequestMapping("/index2")
    public String pageAccueil(Model model){

        String erreur ="";

        model.addAttribute("erreur",erreur);
        return "index2";
    }

    @RequestMapping("/testTree")
    public String pageTree(Model model){
        Set<JSONObject> models = new HashSet<>();
        JSONArray jsonArray = new JSONArray();

        HashMap<String, org.apache.jena.rdf.model.Model> modelHashMap = TdbOperation.unpersistNumberOfModelsMap(TdbOperation.dataSetAnnotated,3);
        Iterator<String> kies = modelHashMap.keySet().iterator();
        while (kies.hasNext())
        {
            String key = kies.next();
            jsonArray.add(ModelUtil.modelToJSON(modelHashMap.get(key),key));
        }
        String erreur ="";
        System.out.println(jsonArray.toJSONString());
        model.addAttribute("models",jsonArray.toJSONString());
        model.addAttribute("erreur",erreur);
        return "treeView";
    }


    @RequestMapping("/cleaning")
    public String Cleaning(Model model){


        System.out.println("je suis n'importe quoi apres nv commit ");
        String erreur ="";

        Map<String, Integer> map= FileOperation.loadYamlFile(timesFilePathTest);

        model.addAttribute("timesMap",map);


        Set<String> kies= map.keySet();
        for (String key:kies){

            System.out.println(key+" : "+map.get(key));
        }

        model.addAttribute("queriesNumbersMap", FileOperation.loadYamlFile(queriesNumberFilePathTest));

        model.addAttribute("erreur",erreur);
        return "cleaning";
    }





}
