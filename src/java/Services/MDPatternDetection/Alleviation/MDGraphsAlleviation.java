package Services.MDPatternDetection.Alleviation;

import Services.MDPatternDetection.ConsolidationClasses.Consolidation;
import Services.MDfromLogQueries.Util.BasicProperties;
import Services.MDfromLogQueries.Util.ModelUtil;
import Services.MDfromLogQueries.Util.TdbOperation;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.vocabulary.OWL;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MDGraphsAlleviation {
    public static void main(String[] args) {
        HashMap<String,Model> modelHashMap = TdbOperation.unpersistModelsMap(TdbOperation.originalDataSet);
        System.out.println("moyenne des tailles avant modification : "+ ModelUtil.averageSize(modelHashMap));
        HashMap<String,Model> modifiedModels = removeUselessProperties(modelHashMap);
        Consolidation.afficherListInformations(modifiedModels);
        System.out.println("moyenne des tailles après modification : "+ ModelUtil.averageSize(modifiedModels));
        TdbOperation.persistNonAnnotated(modifiedModels,TdbOperation.dataSetAlleviated);

    }

   public static HashMap<String, Model> MDGraphsAlleviate (HashMap<String, Model> hashMapModels){

       HashMap<String, Model> MDGraphAlleviated = new HashMap<>();

       int n=0;
       int i=0;

       try {

           Iterator it = hashMapModels.entrySet().iterator();
           while (it.hasNext()) {

               Map.Entry<String, Model> pair = (Map.Entry) it.next();

               if (pair.getValue()!= null && pair.getValue().size()  > 2) {

                   n++;
                   //MDGraphAlleviated.put(pair.getKey(), pair.getValue());

               }
               else {
                   i++;
                   // System.out.println(pair.getValue());
               }


           }

           System.out.println( " le nombre de model >2" +n);
           System.out.println( " le nombre de model <2" +i);

       }catch (NullPointerException ignoredException){

       }
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
                   }
               }
               modifiedModels.put(pair.getKey(), model);

           }

       }catch (NullPointerException ignoredException){

       }
       return modifiedModels;
   }

}
