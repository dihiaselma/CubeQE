package Services.MDfromLogQueries.Util;

import Services.MDPatternDetection.ConsolidationClasses.Consolidation;
import Services.MDfromLogQueries.Declarations.Declarations;
import org.apache.jena.query.Dataset;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.tdb.TDB;
import org.apache.jena.tdb.TDBFactory;

import java.util.*;



public class TdbOperation {
    public static Dataset originalDataSet = TDBFactory.createDataset(Declarations.paths.get("dataSetOriginal"));
    public static Dataset _toString = TDBFactory.createDataset(Declarations.paths.get("_toString"));
    public static Dataset dataSetConsolidate = TDBFactory.createDataset(Declarations.paths.get("dataSetConsolidated"));
    public static Dataset dataSetAnnotated = TDBFactory.createDataset(Declarations.paths.get("dataSetAnnotated"));
    public static Dataset dataSetAnalytic = TDBFactory.createDataset(Declarations.paths.get("dataSetAnalytic"));
    public static Dataset dataSetAnalyticAnnotated = TDBFactory.createDataset(Declarations.paths.get("dataSetAnalyticAnnotated"));
    public static Dataset dataSetAlleviated = TDBFactory.createDataset(Declarations.paths.get("dataSetAlleviated"));
    public static Dataset dataSetAlleviatedUselessProperties = TDBFactory.createDataset(Declarations.paths.get("dataSetAlleviatedUselessProperties"));
    public static Dataset dataSetNonAlleviated = TDBFactory.createDataset(Declarations.paths.get("dataSetNonAlleviated"));
    public static Dataset dataSetEnriched = TDBFactory.createDataset(Declarations.paths.get("dataSetEnriched"));


    public static void updatePaths()
    {
         originalDataSet = TDBFactory.createDataset(Declarations.paths.get("dataSetOriginal"));
         _toString = TDBFactory.createDataset(Declarations.paths.get("_toString"));
         dataSetConsolidate = TDBFactory.createDataset(Declarations.paths.get("dataSetConsolidated"));
         dataSetAnnotated = TDBFactory.createDataset(Declarations.paths.get("dataSetAnnotated"));
         dataSetAnalytic = TDBFactory.createDataset(Declarations.paths.get("dataSetAnalytic"));
         dataSetAnalyticAnnotated = TDBFactory.createDataset(Declarations.paths.get("dataSetAnalyticAnnotated"));
         dataSetAlleviated = TDBFactory.createDataset(Declarations.paths.get("dataSetAlleviated"));
         dataSetAlleviatedUselessProperties = TDBFactory.createDataset(Declarations.paths.get("dataSetAlleviatedUselessProperties"));
         dataSetNonAlleviated = TDBFactory.createDataset(Declarations.paths.get("dataSetNonAlleviated"));
         dataSetEnriched = TDBFactory.createDataset(Declarations.paths.get("dataSetEnriched"));
    }


    public static void main(String... argv) {
        new TdbOperation();
        Declarations.setEndpoint("DogFood");
        //HashMap<String,Model> modelHashMap = unpersistNumberOfModelsMap(dataSetAlleviated,34);
        HashMap<String,Model> modelHashMap ; //unpersistModelsMap(dataSetAnnotated);
        /*//System.out.println(Declarations.paths.get("dataSetConsolidated"));
        Iterator<String> kies = modelHashMap.keySet().iterator();
        System.out.println("to string");
        Consolidation.afficherListInformations(modelHashMap);
      /*  while (kies.hasNext())
        {
            String key = kies.next();
            System.out.println(key);
            //System.out.println(modelHashMap.get(key));
             //  ModelUtil.modelToJSON(modelHashMap.get(key),key);
            //System.out.println(ModelUtil.modelToJSON(modelHashMap.get(key),key).toJSONString());

        }*/

        modelHashMap = unpersistModelsMap(dataSetConsolidate);
        System.out.println("consolides");
        Consolidation.afficherListInformations(modelHashMap);

         /*modelHashMap = unpersistModelsMap(_toString);
        System.out.println("consolides");
        Consolidation.afficherListInformations(modelHashMap);
*/
    }

    public TdbOperation() {
    }



    public static boolean exists(String name, Dataset dt) {
        boolean exists = false;
        // if exists a model with subject.toString == name
        if (dt.containsNamedModel(name)) exists = true;
        else {
            // Verify if it exists as a node inside some model in the tdb
            Iterator<String> it = dt.listNames();
            String subject;

            while (it.hasNext()) {
                subject = it.next();
                Model model = dt.getNamedModel(subject);

                if (model.containsResource(ResourceFactory.createResource(name))) exists = true;

            }
        }
        return exists;
    }


    public static void persistNonAnnotated(HashMap<String, Model> modelHashMap, Dataset originalDataSetStringModel) {
        try {
            //Dataset dataset = DatasetFactory.create(model);

            Iterator it = modelHashMap.entrySet().iterator();
            int nb = 0;
            while (it.hasNext()) {
                nb++;
                System.out.println(" next model " + nb);
                Map.Entry<String, Model> pair = (Map.Entry) it.next();

               // if (exists(pair.getKey(), originalDataSetStringModel)) {
                if (
                        exists(pair.getKey(), originalDataSetStringModel)) {

                    originalDataSetStringModel.getNamedModel(pair.getKey()).add(pair.getValue());

                } else {
                    originalDataSetStringModel.addNamedModel(pair.getKey(), pair.getValue());
                }
            }
            TDB.sync(originalDataSetStringModel);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void persistNonNamedModels(Collection<Model> modelsCollection, Dataset originalDataSetStringModel) {
        try {
            //Dataset dataset = DatasetFactory.create(model);
            Iterator it = modelsCollection.iterator();
            int nb = 0;
            for (Model model : modelsCollection) {
                nb++;
                System.out.println(" next model " + nb);
                originalDataSetStringModel.addNamedModel("model " + nb, model);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void persistHashMap(HashMap<String, Model> modelHashMap, Dataset dataset) {


        try {

            Iterator it = modelHashMap.entrySet().iterator();
            int nb = 0;
            while (it.hasNext()) {
                nb++;

                Map.Entry<String, Model> pair = (Map.Entry) it.next();
                System.out.println("persist next model " + nb);

                if (exists(pair.getKey(), dataset)) {
                    dataset.getNamedModel(pair.getKey()).add(pair.getValue());
                } else {
                    dataset.addNamedModel(pair.getKey(), pair.getValue());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static HashMap<String, Model> unpersistModelsMap(Dataset dataset) {
        HashMap<String, Model> results = new HashMap<>();

        //Dataset dataset = TDBFactory.createDataset(tdbDirectory);
       //g TDB.sync(dataset);
        if (dataset == null) return null;

        Iterator<String> it = dataset.listNames();

        String name;

        try {

            while (it.hasNext()) {
                name = it.next();

                while (name == null) {
                    name = it.next();
                }

                Model model = dataset.getNamedModel(name);

                if ( model != null) results.put(name, model);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("taille de la liste  " + results.size());
        return results;
    }
    public static HashMap<String, Model> unpersistModelsMap(String datasetName) {
        HashMap<String, Model> results = new HashMap<>();

        Dataset dataset = TDBFactory.createDataset(datasetName);
       //g TDB.sync(dataset);
        if (dataset == null) return null;

        Iterator<String> it = dataset.listNames();

        String name;

        try {

            while (it.hasNext()) {
                name = it.next();

                while (name == null) {
                    name = it.next();
                }

                Model model = dataset.getNamedModel(name);

                if ( model != null) results.put(name, model);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("taille de la liste  " + results.size());
        return results;
    }
    public static HashMap<String, Model> unpersistNumberOfModelsMap(Dataset dataset, int number) {
        HashMap<String, Model> results = new HashMap<>();

        //Dataset dataset = TDBFactory.createDataset(tdbDirectory);


        TDB.sync(dataset);
        if (dataset == null) return null;

        Iterator<String> it = dataset.listNames();

        String name;
        int nb = 0;
        try {

            while (it.hasNext() && nb<number) {
                name = it.next();

                while (name == null) {
                    name = it.next();
                }

                Model model = dataset.getNamedModel(name);

                if (name != null && model != null) results.put(name, model);
                nb++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("taille de la liste  " + results.size());
        return results;
    }


    public static ArrayList<String> unpersistListOfNames(Dataset dataset) {

        // dataset=originalDataSet;
        TDB.sync(dataset);

        if (dataset == null) return null;

        ArrayList<String> results = new ArrayList<>();

        Iterator<String> it = dataset.listNames();

        try {
            while (it.hasNext()) {
                String name = it.next();
                while (name == null) {
                    name = it.next();
                }

                results.add(name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("taille de la liste  " + results.size());
        return results;
    }

}
