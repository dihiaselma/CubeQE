package Services.MDfromLogQueries.Util;

import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.DCTerms;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.XSD;

import java.util.HashSet;

public class Datatype_Types {

    public static HashSet<Resource> types;

    public Datatype_Types() {
        types.add(RDF.langString);
        types.add(RDF.xmlLiteral);

    }
}
