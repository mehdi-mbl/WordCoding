package encoder;

import java.util.Arrays;

public class OneHot implements Encoder {

    public int length;

    public OneHot(int length) {
        this.length = length;
    }

    public OneHot() {
        length=0;
    }

    @Override
    public double[] encode(String word) {
        if (word.length()==0){
            throw new IllegalArgumentException("Empty word");
        }else {
            double[] vector;
            if (length==0)  vector= new double[word.length()*26];
            else vector = new double[length];
            Arrays.fill(vector,0);
            for (int i=0;i<word.length();i++){
                double[] a =encode(word.charAt(i));
                for (int j=0; j<a.length; j++){
                    vector[j+(26*i)]=a[j];
                }
            }

            return vector;
        }
    }

    public double[] encode(char a){
        double[] vector = new double[26];
        int index = (Character.getNumericValue(a)-10);
        for (int i=0;i<vector.length;i++){
            if (i==index) vector[i]=1;
            else vector[i]=0;
        }
        return vector;
    }
}
