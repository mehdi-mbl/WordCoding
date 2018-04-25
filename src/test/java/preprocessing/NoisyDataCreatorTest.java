package preprocessing;

import org.junit.*;
import postprocessing.OutputResults;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class NoisyDataCreatorTest {

    private static List<String> correct;
    private static List<String> misspell;
    private static List<String> results;
    private static List<String> data;

    @BeforeClass
    public static void setUp() throws Exception {
        correct= new ArrayList<String>();
        misspell= new ArrayList<String>();
        data= new ArrayList<String>();


        BufferedReader correctReader;
        BufferedReader misspellReader;
        BufferedReader dataReader;
        correctReader = new BufferedReader(new FileReader("/Users/mehdi/Google Drive/PhD/Word coding/Software/src/main/resources/output.txt"));
        misspellReader= new BufferedReader(new FileReader("/Users/mehdi/Google Drive/PhD/Word coding/Software/src/main/resources/input.txt"));
        dataReader= new BufferedReader(new FileReader("/Users/mehdi/Google Drive/PhD/Word coding/Software/src/main/resources/raw_sentences.txt"));
        String lineC;
        String lineM;
        String lineD;
        while((lineC = correctReader.readLine()) !=null && (lineM = misspellReader.readLine()) !=null){
            correct.add(lineC);
            misspell.add(lineM);
        }

        while((lineD = dataReader.readLine()) !=null){
            data.add(lineD);
        }
        correctReader.close();
        misspellReader.close();
        dataReader.close();
    }

    @Test
    public void DistanceTest(){
        //NoisyDataCreator noisyDataCreator= new NoisyDataCreator();
        //results=noisyDataCreator.NoisyData(correct,misspell,data);
    }

    @AfterClass
    public static void tearDown() {
        /*OutputResults outputResults = new OutputResults("raw_sentence_misspellings.txt");

        for (int i =0; i< results.size(); i++){
            outputResults.write(results.get(i));
            outputResults.write("\n");
        }
        outputResults.close();*/
    }
}