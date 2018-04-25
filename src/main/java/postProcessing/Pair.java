package postprocessing;

public class Pair {

    private String Key;
    private Double Value;

    public Pair(String key, Double value) {
        Key = key;
        Value = value;
    }

    public String getKey() {
        return Key;
    }

    public Double getValue() {
        return Value;
    }
}
