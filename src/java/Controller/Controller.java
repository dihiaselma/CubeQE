package Controller;
import Services.MDfromLogQueries.Declarations.Declarations;
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

    private Map<String, Object> times; //= FileOperation.loadYamlFile(timesFilePathTest);
    private Map<String, Object> queriesNumbers; //= FileOperation.loadYamlFile(queriesNumberFilePathTest);
    Declarations declarations = new Declarations("dbPedia");

    @RequestMapping("/")
    public String redirect(){
        return "redirect:/index.j";
    }

    @RequestMapping("/index")
    public String pageAccueil(Model model) {
        Declarations.setEndpoint("dbPedia");
        String error = "";
        model.addAttribute("error", error);
        return "index2";
    }

    @RequestMapping("/test")
    public String testParam(Model model, @RequestParam String uri) {

        String error = "";

        System.out.println("**********  name : "+uri);
        model.addAttribute("error", error);
        return "index2";
    }




    @RequestMapping("/beforeGraphs")
    public String beforeGraphs(Model model) {

        String error = "";

        model.addAttribute("error", error);
        return "subjectsBlocks";
    }

    @RequestMapping("/chooseScenario")
    public String chooseScnerio(Model model) {

        String error = "";

        model.addAttribute("error", error);
        return "chooseScenario";
    }


    @RequestMapping("/cleaning")
    public String Cleaning(Model model, @RequestParam String endpoint) {
        System.out.println(endpoint);
        if (!endpoint.isEmpty())
            Declarations.setEndpoint(endpoint);
        String error = "";
         times = FileOperation.loadYamlFile(timesFilePathTest);
        queriesNumbers = FileOperation.loadYamlFile(queriesNumberFilePathTest);

        System.out.println(Declarations.endpoint+" "+Declarations.root);
        System.out.println(times.get("Deduplication"));
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

    @RequestMapping("/mdGraph")
    public String pageTree(Model model,  @RequestParam String uri) {

        JSONArray jsonArray = new JSONArray();

         org.apache.jena.rdf.model.Model graphModel = TdbOperation.dataSetAnnotated.getNamedModel(uri);

         if (graphModel.size() < 100) jsonArray.add(ModelUtil.modelToJSON(graphModel, uri));


        String erreur = "";


        model.addAttribute("models", jsonArray);
        model.addAttribute("erreur", erreur);
        return "MDGraph";
    }


    @RequestMapping("/subjectsBlocks")
    public String subjectsBlocks(Model model) {
        String error = "Error";

        JSONArray jsonArray = new JSONArray();
        JSONArray jsonArrayGlobal = new JSONArray();

        Iterator<String> it = TdbOperation.dataSetAnnotated.listNames();

        int i = 1;

        while (it.hasNext() && i<20){
       // while (it.hasNext() ){
            String key = it.next();
            JSONObject jsonObject = new JSONObject();

            Resource subject = new ResourceImpl (key);

            jsonObject.put("name", subject.getLocalName());

            jsonObject.put("id", key);
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
