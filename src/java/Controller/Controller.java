package Controller;
import Services.MDfromLogQueries.LogCleaning.LogCleaning;
import Services.MDfromLogQueries.LogCleaning.LogCleaningTemp;
import Services.MDfromLogQueries.LogCleaning.QueriesDeduplicator;
import Services.MDfromLogQueries.SPARQLSyntacticalValidation.SyntacticValidationParallel;
import Services.MDfromLogQueries.Util.FileOperation;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import scala.Int;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static Services.MDfromLogQueries.Declarations.Declarations.*;

@org.springframework.stereotype.Controller

public class Controller {

    @RequestMapping("/index2")
    public String pageAccueil(Model model){

        String erreur ="";

        model.addAttribute("erreur",erreur);
        return "index2";
    }


    @RequestMapping("/cleaning")
    public String Cleaning(Model model){


        System.out.println("je suis n'importe quoi ");
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
