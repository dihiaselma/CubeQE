package Services;

import com.github.jsonldjava.shaded.com.google.common.base.Stopwatch;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

//TODO delete this class

public class AppTest {


    public static void main(String[] args) {

        Stopwatch stopwatch_total = Stopwatch.createStarted();





        File file = new File("C:\\Users\\pc\\Desktop\\PFE\\Files\\wikidata\\ProgramOutput\\Fichier_log_dedup_Nettoye_java.txt");
        String line;
        ArrayList<String> collection = new ArrayList<String>();
        BufferedReader br = null;
        int linesNumbers = 0; // for statistical matters
        try {
            if (!file.isFile()) file.createNewFile();
            br = new BufferedReader(new FileReader(file));

            while ((line = br.readLine()) != null && linesNumbers<10) {
                System.out.println(line);
                linesNumbers++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /*System.out.println("number of lines in the file  :   "+linesNumbers );*/


            stopwatch_total.stop();
            System.out.println("\nTime elapsed for the whole program is \t" + stopwatch_total.elapsed(MILLISECONDS));


        }


    }








