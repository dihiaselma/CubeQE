package Services;

import com.github.jsonldjava.shaded.com.google.common.base.Stopwatch;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

//TODO delete this class

public class AppTest {


    public static void main(String[] args) {

        Stopwatch stopwatch_total = Stopwatch.createStarted();


      stopwatch_total.stop();
        System.out.println("\nTime elapsed for the whole program is \t" + stopwatch_total.elapsed(MILLISECONDS));



    }




}






