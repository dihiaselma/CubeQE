package Services;

import Services.MDfromLogQueries.Declarations.Declarations;
import Services.MDfromLogQueries.Util.FileOperation;
import Services.MDfromLogQueries.Util.TdbOperation;
import com.google.common.base.Stopwatch;
import org.apache.jena.rdf.model.Model;

import javax.naming.event.ObjectChangeListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class test {

    public static void main(String args[]) {
        Declarations.setEndpoint("dbPedia");

        HashSet<String> namedModel = new HashSet<>();
        HashMap<String, Model> stringModelHashMap = new HashMap<>();
        namedModel.add("http://dbpedia.org/class/yago/Film103338821");
        namedModel.add("http://dbpedia.org/class/yago/SoundFilm104261868");
        namedModel.add("http://dbpedia.org/class/yago/TechnicalSchool108285246");
        namedModel.add("http://dbpedia.org/class/yago/Painter110393909");



        namedModel.add("http://dbpedia.org/class/yago/SummerSchool115225526");
        namedModel.add("http://dbpedia.org/ontology/Photographer");
        namedModel.add("http://dbpedia.org/class/yago/Address106356515");
        namedModel.add("http://dbpedia.org/class/yago/Book107954211");
        namedModel.add("http://dbpedia.org/class/yago/DanceMusic107054433");
        namedModel.add("http://dbpedia.org/class/yago/Scholarship113266170");



        namedModel.add("http://dbpedia.org/class/yago/CommunicationSystem103078287");
        namedModel.add("http://dbpedia.org/class/yago/CommunityCenter103078506");
        namedModel.add("http://dbpedia.org/class/yago/Plastic114592610");


        namedModel.add("http://dbpedia.org/class/yago/Spreadsheet106579952");

        for(String s : namedModel) {
            stringModelHashMap.put(s, TdbOperation.dataSetAnnotated.getNamedModel(s));
        }

        TdbOperation.persistHashMap(stringModelHashMap,Declarations.paths.get("dataSetAnnotated")+"selection");
    }


}
