package stringDistance;


import info.debatty.java.stringsimilarity.Jaccard;

public class JaccardIndex implements StringDistance {

    public double distance(String a, String b) {

        Jaccard jaccard= new Jaccard();

        return jaccard.distance(a,b);
    }
}
