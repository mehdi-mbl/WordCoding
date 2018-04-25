package vectorDistance;

import java.io.Serializable;

public class Chebyshev implements VectorDistance, Serializable {

    public double distance(double[] a, double[] b) {
        if (a.length!=b.length) throw new IllegalArgumentException();
        else {
            double d=0;
            for (int i=0; i<a.length;i++){
                if (Math.abs(a[i]-b[i])>d) d= Math.abs(a[i]-b[i]);
            }
            return Math.sqrt(d);
        }
    }

    public double[] derivate(double[] a, double[] b) {
        if (a.length!=b.length) throw new IllegalArgumentException();
        else {
            double[] deriv = new double[a.length];
            int index=0;
            double max =0;
            for (int i=0; i<a.length;i++){
                if (Math.abs(a[i]-b[i])>max) index=i;
            }
            for (int i=0; i<deriv.length;i++){
                if (i==index) deriv[i]=1;
                else deriv[i]=0;
            }
            return deriv;
        }
    }
}
