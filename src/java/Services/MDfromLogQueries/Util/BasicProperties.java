package Services.MDfromLogQueries.Util;

import org.apache.jena.rdf.model.Property;
import org.apache.jena.vocabulary.OWL;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

import java.util.HashSet;

public class BasicProperties {

    public static HashSet<Property> properties = new HashSet<>();

    public BasicProperties() {
        properties.add(RDF.type);
        properties.add(RDFS.label);
        properties.add(RDFS.subClassOf);
        properties.add(OWL.equivalentClass);
        properties.add(OWL.sameAs);
    }
}
