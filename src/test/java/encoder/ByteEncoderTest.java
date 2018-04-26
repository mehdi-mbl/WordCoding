package encoder;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ByteEncoderTest {

    public static byte a;
    public static String word;
    public static ByteEncoder encoder;

    @Before
    public void setUp() throws Exception {
        word="abcd";
        a= word.getBytes()[0];
        encoder= new ByteEncoder(100);
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
        int[] vec= this.encoder.encode(a);
        for (int i=0;i<vec.length;i++){
            System.out.print(vec[i]+" ");
        }
        System.out.println();
    }
}