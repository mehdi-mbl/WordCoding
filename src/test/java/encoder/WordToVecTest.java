package encoder;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class WordToVecTest {

    public static WordToVec wordToVec;

    @BeforeClass
    public static void setUp() throws Exception {
        wordToVec= new WordToVec();
        wordToVec.loadData("raw_sentence_misspellings.txt");

    }

    @Test
    public void DistanceTest(){
        wordToVec.train();

    }

    @AfterClass
    public static void tearDown() {
        wordToVec.writeResults("word2vec.model");

    }

}