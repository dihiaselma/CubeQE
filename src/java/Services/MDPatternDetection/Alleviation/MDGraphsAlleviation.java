package Services.MDPatternDetection.Alleviation;

import org.apache.jena.rdf.model.Model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MDGraphsAlleviation {



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

}
