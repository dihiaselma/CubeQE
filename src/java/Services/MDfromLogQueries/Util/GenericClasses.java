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
        resources.add(RDFS.Resource);
        resources.add(RDFS.Class);
        resources.add(OWL.DatatypeProperty);
        resources.add(OWL.ObjectProperty);
        resources.add(RDF.Property);
        resources.add(RDFS.subPropertyOf);
        resources.add(OWL.AnnotationProperty);
        resources.add(OWL.inverseOf);

    }

}
