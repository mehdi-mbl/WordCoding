package activations;

import java.io.Serializable;

public class Tanh implements ActivationFunction, Serializable {

    public double activate(double input) {
        double a = Math.exp(input);
        double b = Math.exp(-input);
        return ((a-b)/(a+b));
    }

    public double derivate(double input) {
        return 1 - Math.pow(activate(input), 2.0);
    }
}
