package encoder;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OneHotTest {

    public static char a;
    public static String word;
    public static OneHot encoder;


    @Before
    public void setUp() throws Exception {
        a='z';
        word="abcd";
        encoder= new OneHot(150);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void encode() {

        double[] vec= this.encoder.encode(word);
        for (int i=0;i<vec.length;i++){
            System.out.print(vec[i]+" ");
        }
        System.out.println();

    }

    @Test
    public void encode1() {
        double[] vec = this.encoder.encode(a);
        for (int i=0;i<vec.length;i++){
            System.out.print(vec[i]+" ");
        }
        System.out.println();
    }
}