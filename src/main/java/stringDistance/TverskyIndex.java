package stringDistance;

import java.util.HashSet;
import java.util.Set;

public class TverskyIndex implements StringDistance {

    public double distance(String a, String b) {
        double coefficient = 0.0;
        Set<String> x = new HashSet<String>();
        Set<String> y = new HashSet<String>();

        for (int i = 0; i < a.length() - 1; i++) {
            String temp = "" + a.charAt(i) + a.charAt(i+1);
            x.add(temp);
        }

        for(int j =0;j<b.length()-1;j++){
            String temp = "" + b.charAt(j) + b.charAt(j+1);
            y.add(temp);
        }

        Set<String> intersection = new HashSet<String>(x);
        intersection.retainAll(y);
        double commonBigrams = intersection.size();

        Set<String> complimentX = new HashSet<String>(x);
        complimentX.removeAll(y);

        Set<String> complimentY = new HashSet<String>(y);
        complimentY.removeAll(x);

        try{
            coefficient = commonBigrams / (commonBigrams + complimentX.size() + complimentY.size());
        }catch(ArithmeticException e){
            throw new ArithmeticException("Divided by zero");
        }

        return coefficient;
    }
}
