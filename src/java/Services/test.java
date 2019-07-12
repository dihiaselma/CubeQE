package Services;

import Services.MDfromLogQueries.Declarations.Declarations;
import Services.MDfromLogQueries.Util.FileOperation;
import Services.MDfromLogQueries.Util.TdbOperation;
import com.google.common.base.Stopwatch;
import org.apache.jena.query.Dataset;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.tdb.TDBFactory;

import javax.naming.event.ObjectChangeListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class test {

    public static void main(String args[]) {

        Stopwatch stopwatchSelect = Stopwatch.createStarted();

        String endpoint="dbPedia";

        Declarations.setEndpoint(endpoint);


         Dataset destination_dataset=  TDBFactory.createDataset(Declarations.paths.get("dataSetAlleviated20"));


        int nb_models=0;

        HashMap<String, Model> models= TdbOperation.unpersistModelsMap(Declarations.paths.get("dataSetAlleviated2"));
        HashMap<String, Model> models_destination= new HashMap<>();

        try {
            Iterator it = models.entrySet().iterator();

            while (it.hasNext()) {

                Map.Entry<String, Model> pair = (Map.Entry) it.next();

                if (pair.getValue().size()<=20) {
                    models_destination.put(pair.getKey(), pair.getValue()) ;
                    nb_models++;
                }
            }


        }catch(Exception e){

        }
                TdbOperation.persistHashMap(models_destination, destination_dataset);
        System.out.println("nb_models == \t"+nb_models);
        stopwatchSelect.stop();

        System.out.println(" Temps de transformation " + stopwatchSelect);
    }


}
