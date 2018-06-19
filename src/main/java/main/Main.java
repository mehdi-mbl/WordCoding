package main;

import activations.Sigmoid;
import encoder.*;
import postprocessing.Serializer;
import vectorDistance.CosineDivergence;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Main {

    private static List<String> correct;
    private static List<String> misspell;
    private static List<String> correctWithoutDuplicates;
    private static DenoisingAutoEncoder encoder;

    public static void main(String[] args) throws Exception{

        correct= new ArrayList();
        misspell= new ArrayList();


        BufferedReader correctReader;
        BufferedReader misspellReader;
        correctReader = new BufferedReader(new FileReader("output.txt"));
        misspellReader= new BufferedReader(new FileReader("input.txt"));

        String lineC;
        String lineM;
        while((lineC = correctReader.readLine()) !=null && (lineM = misspellReader.readLine()) !=null) {
            correct.add(lineC.toLowerCase());
            misspell.add(lineM.toLowerCase());
        }

        correctWithoutDuplicates = new ArrayList(new HashSet(correct));

        Encoder fallDown = new ByteEncoder(36*8);
        ContextEncoder vector= new ContextEncoder("word2vec.model",fallDown);
        encoder= new DenoisingAutoEncoder(36*8,12,new CosineDivergence(),vector,new Sigmoid());
        StochasticGradientDescent descent = new StochasticGradientDescent(encoder,50,100,0.001,misspell,correct);
        descent.GradientDescent();

        postprocessing.Serializer serializer= new Serializer();
        serializer.serialize(encoder,"encoder.model");

    }
}
