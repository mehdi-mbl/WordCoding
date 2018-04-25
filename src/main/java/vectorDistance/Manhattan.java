package vectorDistance;

import java.io.Serializable;

public class Manhattan  implements  VectorDistance, Serializable {

    public double distance(double[] a, double[] b) {
        if (a.length!=b.length) throw new IllegalArgumentException();
        else {
            double d=0;
            for (int i=0; i<a.length;i++){
                d= d + Math.abs(a[i]-b[i]);
            }
            return d;
        }
    }

    public double[] derivate(double[] a, double[] b) {
        if (a.length!=b.length) throw new IllegalArgumentException();
        else {
            double[] deriv = new double[a.length];
            for (int i=0;i<deriv.length;i++){
                deriv[i]=1;
            }
            return deriv;
        }
    }
}
