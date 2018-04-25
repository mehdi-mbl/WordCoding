package stringDistance;

import org.junit.*;
import postprocessing.OutputResults;
import postprocessing.Pair;
import postprocessing.knn.StringKNN;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MehdiTest {

    private static List<String> correct;
    private static List<String> misspell;
    private static List<String> correctWithoutDuplicates;
    private static List<List<Pair>> results;

    @BeforeClass
    public static void setUp() throws Exception {
        correct= new ArrayList<String>();
        misspell= new ArrayList<String>();


        BufferedReader correctReader;
        BufferedReader misspellReader;
        correctReader = new BufferedReader(new FileReader("output.txt"));
        misspellReader= new BufferedReader(new FileReader("input.txt"));
        String lineC;
        String lineM;
        while((lineC = correctReader.readLine()) !=null && (lineM = misspellReader.readLine()) !=null){
            correct.add(lineC);
            misspell.add(lineM);
        }
        correctWithoutDuplicates = new ArrayList<String>(new HashSet<String>(correct));
        correctReader.close();
        misspellReader.close();

    }

    @Test
    public void DistanceTest(){
        /*results = new ArrayList<List<Pair>>();
        StringKNN knn = new StringKNN(5, new Mehdi());
        for (int i =0; i<misspell.size();i++){
            List<Pair> neighbors = knn.knn(misspell.get(i),correctWithoutDuplicates);
            results.add(neighbors);
        }*/

    }

    @AfterClass
    public static void tearDown() throws Exception {
        /*OutputResults outputResults = new OutputResults("MehdiResults.txt");
        OutputResults outputDistance = new OutputResults("MehdiDistances.txt");
        int performance1=0;
        int performancek=0;

        for (int i =0; i< results.size(); i++){
            boolean exist1=false;
            boolean existk=false;
            if (results.get(i).get(0).getKey().equals(correct.get(i))) exist1=true;
            outputResults.write(misspell.get(i));
            outputResults.write(";");
            for (int j=0; j<results.get(i).size();j++){
                outputResults.write(results.get(i).get(j).getKey());
                outputResults.write(";");
                outputDistance.write(results.get(i).get(j).getValue());
                outputDistance.write(";");
                if (results.get(i).get(j).getKey().equals(correct.get(i))) existk=true;
            }
            outputDistance.write("\n");
            outputResults.write("\n");
            if (exist1) performance1++;
            if (existk) performancek++;

        }
        outputResults.write((double) performance1/results.size());
        outputResults.write("\n");
        outputResults.write((double) performancek/results.size());
        outputResults.close();
        outputDistance.close();*/
    }
}