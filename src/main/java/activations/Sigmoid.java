package activations;

import java.io.Serializable;

public class Sigmoid implements ActivationFunction , Serializable{

    public double activate(double input) {
        return 1.0 / (1 + Math.exp(-1.0 * input));
    }

    public double derivate(double input) {
        return activate(input) * (1.0 - activate(input));
    }
}
