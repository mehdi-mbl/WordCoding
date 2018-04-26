package stringDistance;

import info.debatty.java.stringsimilarity.OptimalStringAlignment;

public class OptimalAlignment implements StringDistance {

    @Override
    public double distance(String a, String b) {
        OptimalStringAlignment optimalStringAlignment = new OptimalStringAlignment();
        return optimalStringAlignment.distance(a,b);
    }
}
