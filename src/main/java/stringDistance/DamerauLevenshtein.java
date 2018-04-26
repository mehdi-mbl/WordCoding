package stringDistance;

import info.debatty.java.stringsimilarity.Damerau;

public class DamerauLevenshtein implements StringDistance {

    @Override
    public double distance(String a, String b) {
        Damerau damerau = new Damerau();
        return damerau.distance(a,b);
    }
}
