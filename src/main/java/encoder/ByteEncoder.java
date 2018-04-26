package encoder;


import java.util.Arrays;

public class ByteEncoder implements Encoder {

    public int length;

    public ByteEncoder(int length) {
        this.length = length;
    }

    public ByteEncoder() {
        this.length=0;
    }

    @Override
    public double[] encode(String word) {

        if (word.length()==0){
            throw new IllegalArgumentException("Empty word");
        }else {

            double[] vector;
            if (length==0)  vector= new double[word.length()*8];
            else vector = new double[length];
            Arrays.fill(vector,0);
            byte[] a = word.getBytes();

            for (int i=0;i<word.length();i++){
                int[] b =encode(a[i]);
                for (int j=0; j<b.length; j++){
                    vector[j+(8*i)]=b[j];
                }
            }

            return vector;
        }
    }

    public int[] encode(byte a){
        int[] vector= new int[8];
        String s1 = String.format("%8s", Integer.toBinaryString(a & 0xFF)).replace(' ', '0');
        for (int i=0;i<vector.length;i++){
            String s2= String.valueOf(s1.charAt(i));
            vector[i]=Integer.parseInt(s2);
        }
        return vector;
    }
}
