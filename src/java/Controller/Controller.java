package Controller;
import Services.MDfromLogQueries.LogCleaning.LogCleaning;
import Services.MDfromLogQueries.LogCleaning.LogCleaningTemp;
import Services.MDfromLogQueries.LogCleaning.QueriesDeduplicator;
import Services.MDfromLogQueries.SPARQLSyntacticalValidation.SyntacticValidationParallel;
import Services.MDfromLogQueries.Util.FileOperation;
import Services.MDfromLogQueries.Util.ModelUtil;
import Services.MDfromLogQueries.Util.TdbOperation;
import com.github.jsonldjava.utils.Obj;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import scala.Int;

import java.util.*;

import static Services.MDfromLogQueries.Declarations.Declarations.*;

@org.springframework.stereotype.Controller

public class Controller {

    private Map<String, Object> times= FileOperation.loadYamlFile(timesFilePathTest);
    private Map<String, Object> queriesNumbers = FileOperation.loadYamlFile(queriesNumberFilePathTest);


    @RequestMapping("/index")
    public String pageAccueil(Model model){

        String erreur ="";

        model.addAttribute("erreur",erreur);
        return "index2";
    }

    @RequestMapping("/beforeGraphs")
    public String beforeGraphs(Model model){

        String erreur ="";

        model.addAttribute("erreur",erreur);
        return "beforeGraphs";
    }



    @RequestMapping("/cleaning")
    public String Cleaning(Model model){

        String erreur ="";

        model.addAttribute("timesMap", times);

        model.addAttribute("queriesNumbersMap", queriesNumbers);

        model.addAttribute("erreur",erreur);
        return "cleaning";
    }

    @RequestMapping("/execution")
    public String executing(Model model){

        String erreur ="";

        model.addAttribute("timesMap", times);

        model.addAttribute("queriesNumbersMap", queriesNumbers);

        model.addAttribute("erreur",erreur);
        return "execution";
    }

    @RequestMapping("/testTree")
    public String pageTree(Model model){
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



}
