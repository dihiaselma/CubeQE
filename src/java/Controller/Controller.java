package Controller;
import Services.MDfromLogQueries.LogCleaning.LogCleaning;
import Services.MDfromLogQueries.LogCleaning.LogCleaningTemp;
import Services.MDfromLogQueries.LogCleaning.QueriesDeduplicator;
import Services.MDfromLogQueries.SPARQLSyntacticalValidation.SyntacticValidationParallel;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;

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


        model.addAttribute("cleanedQueriesNumber", LogCleaning.queriesNumber());
        model.addAttribute("queriesNumber", LogCleaning.nb_queries());

        model.addAttribute("dedupQueriesNumber", QueriesDeduplicator.queriesNumber);


        model.addAttribute("validatedQueriesNumber", SyntacticValidationParallel.queriesNumber());

        String erreur ="";
        model.addAttribute("erreur",erreur);
        return "cleaning";
    }





}
