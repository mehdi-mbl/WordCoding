package stringDistance;

import info.debatty.java.stringsimilarity.NGram;

public class Ngram implements StringDistance {

    @Override
    public double distance(String a, String b) {
        NGram twogram = new NGram(2);
        return twogram.distance(a,b);
    }
}
