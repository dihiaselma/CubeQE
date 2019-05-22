package Services.MDfromLogQueries.Util;

import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.OWL;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

import java.util.HashSet;

public class GenericClasses {

    public static HashSet<Resource> resources = new HashSet<>();

    public GenericClasses() {
        resources.add(OWL.Class);
        resources.add(OWL.Thing);
    }

}
