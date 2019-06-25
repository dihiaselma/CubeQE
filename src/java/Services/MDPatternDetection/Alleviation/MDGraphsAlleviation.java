package Services.MDPatternDetection.Alleviation;

import Services.MDPatternDetection.ConsolidationClasses.Consolidation;
import Services.MDfromLogQueries.Declarations.Declarations;
import Services.MDfromLogQueries.Util.BasicProperties;
import Services.MDfromLogQueries.Util.GenericClasses;
import Services.MDfromLogQueries.Util.ModelUtil;
import Services.MDfromLogQueries.Util.TdbOperation;
import org.apache.jena.rdf.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class MDGraphsAlleviation {

    public static int numberModelsAlleviated=0;
    public static int numberModelsRemoved=0;
    public static int numberStatementRemoved = 0;
    public static int numberModelAlleviated = 0 ;


    public static void main(String[] args) {
        HashMap<String, Model> modelHashMap = TdbOperation.unpersistModelsMap(TdbOperation.dataSetAnnotated);
        System.out.println("moyenne des tailles avant modification : " + ModelUtil.averageSize(modelHashMap));
        HashMap<String, Model> modifiedModels = removeUselessProperties(modelHashMap);
        Consolidation.afficherListInformations(modifiedModels);
        System.out.println("moyenne des tailles après modification : " + ModelUtil.averageSize(modifiedModels));
        TdbOperation.persistNonAnnotated(modifiedModels, TdbOperation.dataSetAlleviated);
    }



   public static HashMap<String, Model> MDGraphsAlleviate (HashMap<String, Model> hashMapModels){

       HashMap<String, Model> MDGraphAlleviated = new HashMap<>();
       HashMap<String, Model> MDGraphLessThen2 = new HashMap<>();
       long size =0;
       int nb=0;
        try {


            for (Map.Entry<String, Model> pair : hashMapModels.entrySet()) {
                size = pair.getValue().size();
                if (pair.getValue() != null && size > 2 && size<20 && !Pattern.compile(Pattern.quote("wikicat"), Pattern.CASE_INSENSITIVE)
                        .matcher(pair.getKey()).find()) {

                    MDGraphAlleviated.put(pair.getKey(), pair.getValue());

                } else {
                    if (size >20)
                    {
                        Model reducedModel = ModelFactory.createDefaultModel();
                        StmtIterator it = pair.getValue().listStatements();
                        int nbStm = 0;
                        while (it.hasNext() && nbStm<10)
                        {
                            nbStm++;
                            reducedModel.add(it.next());
                        }
                        MDGraphAlleviated.put(pair.getKey(), reducedModel);
                    }
                    //MDGraphLessThen2.put(pair.getKey(), pair.getValue());
                }
                nb++;

                System.out.println("next : "+nb);

            }

       }catch (NullPointerException ignoredException){

       }

       // TODO à enlever si y a pas besoin de sauvegarder
       //TdbOperation.persistHashMap(MDGraphAlleviated, TdbOperation.dataSetAlleviated);
       //TdbOperation.persistHashMap(MDGraphLessThen2, Declarations.paths.get("dataSetNonAlleviated"));

       numberModelsAlleviated+=MDGraphAlleviated.size();
       numberModelsRemoved+=MDGraphLessThen2.size();

       return MDGraphAlleviated;
   }


    public static HashMap<String,Model > removeRedundantTriples(HashMap<String,Model> modelHashMap)
    {
        Model model;
        for (Object o : modelHashMap.entrySet()) {
            Map.Entry<String, Model> pair = (Map.Entry) o;
            model = pair.getValue();
            List<Statement> stmtList = model.listStatements().toList();
            for (Statement statement : stmtList) {
                for (Statement statement1 : stmtList)
                {
                    if (statement.getObject().asResource().getLocalName().equals(statement1.getObject().asResource().getLocalName()))
                    {
                        model.remove(statement1);
                        model.add(statement1.getSubject(),statement1.getPredicate(),statement.getObject());
                    }
                    if (statement.getSubject().asResource().getLocalName().equals(statement1.getSubject().asResource().getLocalName()))
                    {
                        model.remove(statement1);
                        model.add(statement.getSubject(),statement1.getPredicate(),statement1.getObject());
                    }
                    if (statement.getPredicate().asResource().getLocalName().equals(statement1.getPredicate().asResource().getLocalName()))
                    {
                        model.remove(statement1);
                        model.add(statement1.getSubject(),statement.getPredicate(),statement1.getObject());
                    }
                }

            }

        }
        return modelHashMap;
    }


   public static HashMap<String,Model> removeUselessProperties(HashMap<String,Model> modelHashMap)
   {
       new BasicProperties();
       new GenericClasses();
       HashMap<String,Model> modifiedModels = new HashMap<>();
       Model model;
       Boolean alleviated = false;
       try {

           for (Object o : modelHashMap.entrySet()) {
               Map.Entry<String, Model> pair = (Map.Entry) o;
               model = pair.getValue();
               List<Statement> stmtList = model.listStatements().toList();
               for (Statement statement : stmtList) {
                   if (BasicProperties.properties.contains(statement.getPredicate())
                   || GenericClasses.resources.contains(statement.getObject().asResource())
                   || GenericClasses.resources.contains(statement.getSubject())) {
                       alleviated = true;
                       model.remove(statement);
                       numberStatementRemoved++;
                   }

               }
               if (alleviated)
                   numberModelAlleviated++;
               alleviated = false;
               modifiedModels.put(pair.getKey(), model);

           }

       }catch (NullPointerException ignoredException){

       }
       return modifiedModels;
   }
    public static HashMap<String,Model> getModelsByTheme(HashMap<String,Model> modelHashMap, String theme)
    {
        HashMap<String,Model> modifiedModels = new HashMap<>();
        int nb = 0 ;
        try {

            for (Object o : modelHashMap.entrySet()) {
                Map.Entry<String, Model> pair = (Map.Entry) o;
                if (Pattern.compile(Pattern.quote(theme), Pattern.CASE_INSENSITIVE)
                        .matcher(pair.getKey()).find())
                {
                    System.out.println("Model by theme"+ nb++);
                    modifiedModels.put(pair.getKey(),pair.getValue());
                }

            }

        }catch (NullPointerException ignoredException){

        }
        return modifiedModels;
    }



}
