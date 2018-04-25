package vectorDistance;

import java.io.Serializable;

public class CosineDivergence implements VectorDistance, Serializable {

    public double distance(double[] a, double[] b) {
        if (a.length!=b.length) throw new IllegalArgumentException();
        else {
            return multiply(a,b)/(norm(a)*norm(b)+1e-6);
        }
    }

    private double multiply (double[] a, double[] b){
        double n=0;
        for (int i=0; i<a.length;i++){
            n+=a[i]*b[i];
        }
        return n;
    }

    private double norm (double[] a){
        double n=0;
        for (int i=0; i<a.length;i++){
            n+=Math.pow(a[i],2);
        }
        return Math.sqrt(n);
    }

    public double[] derivate(double[] a, double[] b) {
        if (a.length!=b.length) throw new IllegalArgumentException();
        else {
            double[] deriv = new double[a.length];
            for (int i=0;i<deriv.length;i++){
                deriv[i]=b[i]/(norm(a)*norm(b))-(a[i]/Math.pow(norm(a),2))*distance(a,b);
            }
            return deriv;
        }
    }
}
