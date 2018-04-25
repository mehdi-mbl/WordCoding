package encoder;

import activations.Sigmoid;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import postprocessing.Serializer;
import preprocessing.LoadFile;
import vectorDistance.CosineDivergence;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class DenoisingAutoencoderTester {

    private static List<String> correct;
    private static List<String> misspell;
    private static List<String> correctWithoutDuplicates;
    private static DenoisingAutoEncoder encoder;

    @BeforeClass
    public static void setUpBeforeClass() throws IOException{
        correct= new ArrayList();
        misspell= new ArrayList();


        BufferedReader correctReader;
        BufferedReader misspellReader;
        correctReader = new BufferedReader(new FileReader("output.txt"));
        misspellReader= new BufferedReader(new FileReader("input.txt"));

        String lineC;
       String lineM;
       while((lineC = correctReader.readLine()) !=null && (lineM = misspellReader.readLine()) !=null) {
           correct.add(lineC);
           misspell.add(lineM);
       }

        correctWithoutDuplicates = new ArrayList(new HashSet(correct));

    }

    @Test
    public void EncodingTest() throws Exception{
        //ContextEncoder vector= new ContextEncoder("word2vec.modelModel");
        //encoder= new DenoisingAutoEncoder(100,20,new CosineDivergence(),vector,new Sigmoid());
        //StochasticGradientDescent descent = new StochasticGradientDescent(encoder,50,100,0.001,misspell,correct,correctWithoutDuplicates);
        //descent.MultithreadGradientDescent();
        //descent.NewGradientDescent();

    }

    @AfterClass
    public static void afterClass() throws Exception{
        //Serializer serializer= new Serializer();
        //serializer.serialize(encoder,"encoder.model");

    }

}
