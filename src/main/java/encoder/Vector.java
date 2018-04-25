package encoder;

public class Vector implements Encoder {

    private int length;

    public Vector(int length) {
        this.length = length;
    }

    public Vector(){
        this.length=0;
    }

    public double[] encode(String word) {
        if (this.length==0){
            double[] vector = new double[word.length()];
            for (int j=0; j<vector.length; j++){
                vector[j]=(double) Character.getNumericValue(word.toCharArray()[j]);
                vector[j]=(vector[j]-63)/63;
            }
            return vector;
        }else {
            double[] vector = new double[this.length];
            for (int j=0; j<vector.length; j++){
                if (j<word.length()){
                    vector[j]=(double) Character.getNumericValue(word.toCharArray()[j]);
                    vector[j]=(vector[j]-63)/63;
                }
                else vector[j]=0;
            }
            return vector;
        }

    }

    public int getLength() {
        return length;
    }
}
