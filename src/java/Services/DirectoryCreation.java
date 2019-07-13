package Services;

import Services.MDfromLogQueries.Declarations.Declarations;
import Services.MDfromLogQueries.Util.FileOperation;

public class DirectoryCreation {
    public static void main(String args[]) {
        String endpoint = "DogFood";


        Declarations.setEndpoint(endpoint);
        for (String key : Declarations.paths.keySet())
        {
            if (!Declarations.paths.get(key).contains("."))
                FileOperation.createDirectory(Declarations.paths.get(key));
        }
    }

}
