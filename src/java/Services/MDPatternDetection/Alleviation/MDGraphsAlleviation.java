package Services.MDPatternDetection.Alleviation;

import Services.MDPatternDetection.ConsolidationClasses.Consolidation;
import Services.MDfromLogQueries.Util.BasicProperties;
import Services.MDfromLogQueries.Util.ModelUtil;
import Services.MDfromLogQueries.Util.TdbOperation;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Statement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MDGraphsAlleviation {

    public static int numberModelsAlleviated=0;
    public static int numberModelsRemoved=0;
    public static int numberStatementRemoved = 0;


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

        try {


            for (Map.Entry<String, Model> pair : hashMapModels.entrySet()) {

                if (pair.getValue() != null && pair.getValue().size() > 2) {

                    MDGraphAlleviated.put(pair.getKey(), pair.getValue());

                } else {
                    MDGraphLessThen2.put(pair.getKey(), pair.getValue());
                }

            }

       }catch (NullPointerException ignoredException){

       }

       // TODO à enlever si y a pas besoin de sauvegarder
       TdbOperation.persistHashMap(MDGraphAlleviated, TdbOperation.dataSetAlleviated);
       TdbOperation.persistHashMap(MDGraphLessThen2, TdbOperation.dataSetNonAlleviated);



       numberModelsAlleviated+=MDGraphAlleviated.size();
       numberModelsRemoved+=MDGraphLessThen2.size();

       return MDGraphAlleviated;
   }



   public static HashMap<String,Model> removeUselessProperties(HashMap<String,Model> modelHashMap)
   {
       new BasicProperties();
       HashMap<String,Model> modifiedModels = new HashMap<>();
       Model model;
       try {

           for (Object o : modelHashMap.entrySet()) {
               Map.Entry<String, Model> pair = (Map.Entry) o;
               model = pair.getValue();
               List<Statement> stmtList = model.listStatements().toList();
               for (Statement statement : stmtList) {
                   if (BasicProperties.properties.contains(statement.getPredicate())) {
                       model.remove(statement);
                       numberStatementRemoved++;
                   }
               }
               modifiedModels.put(pair.getKey(), model);

           }

       }catch (NullPointerException ignoredException){

       }
       return modifiedModels;
   }

}
