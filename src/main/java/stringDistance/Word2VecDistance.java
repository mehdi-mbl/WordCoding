package stringDistance;

import encoder.ContextEncoder;
import vectorDistance.VectorDistance;

public class Word2VecDistance implements StringDistance {

    private ContextEncoder encoder;

    private VectorDistance vectorDistance;

    public Word2VecDistance(ContextEncoder encoder, VectorDistance distance){
        this.encoder=encoder;
        this.vectorDistance=distance;

    }

    public double distance(String a, String b) {
        if (this.encoder.encode(a)==null || this.encoder.encode(b)==null) return Double.POSITIVE_INFINITY;
        else return this.vectorDistance.distance(this.encoder.encode(a),this.encoder.encode(b));
    }

}
