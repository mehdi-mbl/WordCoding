package stringDistance;

import info.debatty.java.stringsimilarity.QGram;

public class Qgram implements StringDistance {

    @Override
    public double distance(String a, String b) {
        QGram qGram= new QGram(2);
        return qGram.distance(a,b);
    }
}
