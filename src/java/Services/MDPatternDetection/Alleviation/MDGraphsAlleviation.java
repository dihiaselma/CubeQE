package Services.MDPatternDetection.Alleviation;

import Services.MDfromLogQueries.Util.TdbOperation;
import org.apache.jena.rdf.model.Model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MDGraphsAlleviation {

 public static int numberModelsAlleviated=0;
 public static int numberModelsRemoved=0;


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

}
