package stringDistance;

import info.debatty.java.stringsimilarity.Cosine;

public class CosineSimilarity implements StringDistance {

    public double distance(String a, String b) {

        Cosine cosine= new Cosine();
        return cosine.distance(a,b);
    }
}
