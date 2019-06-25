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
        properties.add(OWL.inverseOf);
        properties.add(RDFS.subPropertyOf);
        properties.add(RDFS.range);
        properties.add(RDFS.domain);
        properties.add(RDFS.comment);
        properties.add(OWL.hasValue);
    }
}
