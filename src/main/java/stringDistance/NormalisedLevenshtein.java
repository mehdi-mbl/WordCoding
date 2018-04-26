package stringDistance;

import info.debatty.java.stringsimilarity.NormalizedLevenshtein;

public class NormalisedLevenshtein implements StringDistance {

    @Override
    public double distance(String a, String b) {
        NormalizedLevenshtein normalizedLevenshtein = new NormalizedLevenshtein();
        return normalizedLevenshtein.distance(a,b);
    }
}
