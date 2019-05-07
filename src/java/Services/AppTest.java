package Services;

import Services.MDfromLogQueries.Declarations.Declarations;

//TODO delete this class

public class AppTest {


    public static void main(String[] args) {

      //  Stopwatch stopwatch_total = Stopwatch.createStarted();


        System.out.println(Declarations.endpoint);
        System.out.println(Declarations.root);
        System.out.println(Declarations.root2);

        Declarations.setEndpoint("dogfood");

       // Declarations.endpoint= "dogfood";

        System.out.println(Declarations.endpoint);
        System.out.println(Declarations.root);
        System.out.println(Declarations.root2);


       /* stopwatch_total.stop();
        System.out.println("\nTime elapsed for the whole program is \t" + stopwatch_total.elapsed(MILLISECONDS));*/



    }




}






