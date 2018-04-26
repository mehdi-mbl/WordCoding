package stringDistance;

import info.debatty.java.stringsimilarity.CharacterSubstitutionInterface;

public class WeightedLevenshtein implements StringDistance {

    @Override
    public double distance(String a, String b) {
        info.debatty.java.stringsimilarity.WeightedLevenshtein wl = new info.debatty.java.stringsimilarity.WeightedLevenshtein(
                new CharacterSubstitutionInterface() {
                    public double cost(char c1, char c2) {
                        return 1.0;
                    }
                });

        return wl.distance(a,b);
    }
}
