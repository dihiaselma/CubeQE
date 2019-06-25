package Controller;

import Services.MDfromLogQueries.Declarations.Declarations;
import Services.MDfromLogQueries.Util.FileOperation;
import Services.MDfromLogQueries.Util.ModelUtil;
import Services.MDfromLogQueries.Util.TdbOperation;
import Services.Statistics.Statistics1;
import org.apache.jena.query.Dataset;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.impl.ResourceImpl;
import org.apache.jena.tdb.TDBFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;


@org.springframework.stereotype.Controller

public class Controller {

    private Map<String, Object> times;
    private Map<String, Object> queriesNumbers;
    private HashMap<String, Object> statisticsTotal;
    private HashMap<String, Object> statisticsByModel = new HashMap<>();
    private HashMap<String, Object> enrichedStatisticsByModel = new HashMap<>();
    // ToDo change the file name
    private HashMap<String, Object> statisticsTotalEnrichment;

    private HashMap<String, String> statDescriptions = new HashMap<>();

    private void initialiseStatisticsList() {

        statDescriptions.put("NC", "Total number of classes of the star S.");
        statDescriptions.put("NFC", "Number of fact classes of the Start S.");
        statDescriptions.put("NDC", "Number of dimension classes of the star S.");
        statDescriptions.put("NBC", "Number of base classes (dimension hierarchy levels ) of the star S.");


        statDescriptions.put("NAFC", "Number of Fact Attributes of the fact class of the star S.");
        statDescriptions.put("NADC", "Number of Dimension Attributes of the dimension classes of the star S.");
        statDescriptions.put("NABC", "Number of Dimension Attributes of the base classes of the star.");

        statDescriptions.put("NH", "Number of hierarchy relationships of the star S.");
        statDescriptions.put("NA", "Total number of Fact Attributes and Dimension attributes of the star S.");

        statDescriptions.put("DHP", "Maximum depth of the hierarchy relationships of the star S.");

        statDescriptions.put("RBC", "Ratio of base classes. Number of base classes per dimension class of the star S.");
        statDescriptions.put("RSA", "Ratio of attributes of the star S. (Number of attributes Fact Attributes) / ( number of Dimension attributes).");


        statDescriptions.put("NMH", "Number of multiple hierarchies in the schema.");
        statDescriptions.put("NLDH", "Number of levels in dimension hierarchies of the schema.");
        statDescriptions.put("NAPMH", "Number of alternate paths in multiple hierarchies of the schema.");
        statDescriptions.put("NDSH", "Number of dimensions involved in shared hierarchies of the schema.");
        statDescriptions.put("NSH", "Number of shared hierarchies of the schema.");
        statDescriptions.put("NSLWD", "Number of Shared Levels Within Dimensions.");
        statDescriptions.put("NSLBD", "Number of Shared Levels between Dimensions within a Fact Scheme.");

        statDescriptions.put("NSLAF", "Number of Shared Levels between Dimensions across Different Fact Schemes.");
        statDescriptions.put("NNSH", "Number of Non-Strict Hierarchies.");
        statDescriptions.put("CM1", "This metric appraises the coupling that occurs due to the statDescriptions.put(eraction between the classes and their attributes in the multidimensional  the conceptual model.");
        statDescriptions.put("CM2", "It quantifies the coupling due to inheritance among the conceptual model classes. The classes which are related by inheritance form a hierarchy called the generalization hierarchy.");
        statDescriptions.put("MMCM", "Multidimensional model complexity metric.");
    }

    @RequestMapping("/")
    public String redirect() {
        return "redirect:/index.j";
    }

    @RequestMapping("/index")
    public String pageAccueil(Model model) {
        initialiseStatisticsList();
        String error = "";

        model.addAttribute("error", error);
        return "index2";
    }

    @RequestMapping("/test")
    public String testParam(Model model, @RequestParam String uri) {

        String error = "";

        System.out.println("**********  name : " + uri);
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

        //TODO remttre les vrais chemins
//        times = FileOperation.loadYamlFile(Declarations.paths.get("timesFilePath"));
        //      queriesNumbers = FileOperation.loadYamlFile(Declarations.paths.get("queriesNumberFilePath"));

        times = FileOperation.loadYamlFile(Declarations.paths.get("timesFilePath"));
        queriesNumbers = FileOperation.loadYamlFile(Declarations.paths.get("queriesNumberFilePath"));

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
    public String pageTree(Model model, @RequestParam String uri) {

        JSONArray jsonArray = new JSONArray();
        uri = uri.replace("__", "#");
        //Dataset dataset = TDBFactory.createDataset(Declarations.paths.get("dataSetAnnotated")+"university");

        org.apache.jena.rdf.model.Model graphModel = TdbOperation.dataSetAnnotated.getNamedModel(uri);
        //org.apache.jena.rdf.model.Model graphModel = dataset.getNamedModel(uri);

        if (statisticsByModel.size()==0)
            statisticsByModel = (HashMap<String, Object>) FileOperation.loadYamlFile(Declarations.paths.get("statisticsFileYAML"));
        model.addAttribute("statistics", (HashMap<String,Object>) statisticsByModel.get(uri));
        model.addAttribute("statisticsDescription", statDescriptions);
        model.addAttribute("uri",uri);

        if (graphModel.size() < 200) jsonArray.add(ModelUtil.modelToJSON(graphModel, uri));

        System.out.println(jsonArray.toJSONString());
        String erreur = "";


        model.addAttribute("models", jsonArray);
        model.addAttribute("erreur", erreur);
        return "MDGraph";
    }

    @RequestMapping("/mdGraphEnriched")
    public String pageTreeEnriched(Model model, @RequestParam String uri) {

        JSONArray jsonArray = new JSONArray();
        uri = uri.replace("__", "#");

        org.apache.jena.rdf.model.Model graphModel = TdbOperation.dataSetEnrichedAnnotated.getNamedModel(uri);
        if (enrichedStatisticsByModel.size()==0)
            enrichedStatisticsByModel = (HashMap<String, Object>) FileOperation.loadYamlFile(Declarations.paths.get("enrichedStatisticsFileYAML"));
        model.addAttribute("statistics", (HashMap<String,Object>) enrichedStatisticsByModel.get(uri));
        model.addAttribute("statisticsDescription", statDescriptions);

        if (graphModel.size() < 200) jsonArray.add(ModelUtil.modelToJSON(graphModel, uri));

        System.out.println(jsonArray.toJSONString());
        String erreur = "";


        model.addAttribute("models", jsonArray);
        model.addAttribute("erreur", erreur);
        return "MDGraphEnriched";
    }


    @RequestMapping("/subjectsBlocks")
    public String subjectsBlocks(Model model) {
        String error = "Error";
        //Declarations.setEndpoint("DogFood");
        JSONArray jsonArray = new JSONArray();
        JSONArray jsonArrayGlobal = new JSONArray();

      //  Dataset dataset = TDBFactory.createDataset(Declarations.paths.get("dataSetAnnotated")+"university");

        Iterator<String> it = TdbOperation.dataSetAnnotated.listNames();
       // Iterator<String> it = dataset.listNames();


        int i = 1;

        while (it.hasNext() && i <= 36) {
            // while (it.hasNext() ){


            String key = it.next();
            JSONObject jsonObject = new JSONObject();

            Resource subject = new ResourceImpl(key);

            jsonObject.put("name", subject.getLocalName());

            jsonObject.put("id", key.replace("#", "__"));
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


    @RequestMapping("/statistics")
    public String statistics(Model model) {

        String error = "";

        statisticsTotal = (HashMap<String, Object>) FileOperation.loadYamlFile(Declarations.paths.get("statisticsByTypeFile"));

        model.addAttribute("statistics", statisticsTotal);
        model.addAttribute("statisticsDescription", statDescriptions);

        model.addAttribute("error", error);
        return "statistics";
    }


    @RequestMapping("/statisticsEnrichment")
    public String statisticsEnrichment(Model model) {

        String error = "";

        statisticsTotal = (HashMap<String, Object>) FileOperation.loadYamlFile(Declarations.paths.get("statisticsByTypeFile"));
        statisticsTotalEnrichment = (HashMap<String, Object>) FileOperation.loadYamlFile(Declarations.paths.get("enrichedStatisticsByTypeFile"));
        model.addAttribute("statistics", statisticsTotal);
        model.addAttribute("statisticsEnrichment", statisticsTotalEnrichment);
        model.addAttribute("statisticsEnrichmentDescription", statDescriptions);

        model.addAttribute("error", error);
        return "scenarioEnrichment";
    }

    @RequestMapping("/analyticScenario")
    public String analyticScenario(Model model) {

        String error = "";


        times = FileOperation.loadYamlFile(Declarations.paths.get("timesFilePath"));
        queriesNumbers = FileOperation.loadYamlFile(Declarations.paths.get("queriesNumberFilePath"));
        model.addAttribute("timesMap", times);
        model.addAttribute("queriesNumbersMap", queriesNumbers);

        statisticsTotal = (HashMap<String, Object>) FileOperation.loadYamlFile(Declarations.paths.get("analyticStatisticsByTypeFile"));
        model.addAttribute("statistics", statisticsTotal);
        model.addAttribute("statisticsDescription", statDescriptions);

        return "analyticScenario";
    }

    @RequestMapping("/subjectsBlocksAnalytic")
    public String subjectsBlocksAnalytic(Model model) {
        String error = "Error";
        //Declarations.setEndpoint("DogFood");
        JSONArray jsonArray = new JSONArray();
        JSONArray jsonArrayGlobal = new JSONArray();


        Iterator<String> it = TdbOperation.dataSetAnalyticAnnotated.listNames();

        int i = 1;

        while (it.hasNext()&& i<30) {
            String key = it.next();
            org.apache.jena.rdf.model.Model modelRDF = TdbOperation.dataSetAnalyticAnnotated.getNamedModel(key);
            JSONObject jsonObject = new JSONObject();

            Resource subject = modelRDF.listSubjects().next();

            jsonObject.put("name", subject.getLocalName());

            jsonObject.put("id", key.replace("#", "__"));
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
        return "subjectBlocksAnalytic";
    }


    @RequestMapping("/mdGraphAnalytic")
    public String analyticGraph(Model model, @RequestParam String uri) {

        JSONArray jsonArray = new JSONArray();
        uri = uri.replace("__", "#");

        org.apache.jena.rdf.model.Model graphModel = TdbOperation.dataSetAnalyticAnnotated.getNamedModel(uri);


        System.out.println(graphModel.toString());

        if (graphModel.size() < 200) jsonArray.add(ModelUtil.modelToJSON(graphModel, uri));

        System.out.println(jsonArray.toJSONString());
        String erreur = "";


        model.addAttribute("models", jsonArray);
        model.addAttribute("erreur", erreur);
        return "mdGraphAnalytic";
    }



    @RequestMapping("/graphsTable")
    public String dataTable(Model model) {
        String error = "Error";


        List<Resource> listNames = new ArrayList<>();
        Iterator<String> it = TdbOperation.dataSetAnnotated.listNames();


        int i = 1;

      while (it.hasNext())
      {
          listNames.add(new ResourceImpl(it.next()));
      }

        model.addAttribute("subjects", listNames);
        model.addAttribute("error", error);
        return "graphsTable";
    }
}
