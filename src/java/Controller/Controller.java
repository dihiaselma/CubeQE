package Controller;
import Services.MDfromLogQueries.LogCleaning.LogCleaning;
import Services.MDfromLogQueries.LogCleaning.LogCleaningTemp;
import Services.MDfromLogQueries.LogCleaning.QueriesDeduplicator;
import Services.MDfromLogQueries.SPARQLSyntacticalValidation.SyntacticValidationParallel;
import Services.MDfromLogQueries.Util.FileOperation;
import com.github.jsonldjava.utils.Obj;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import scala.Int;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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



}
