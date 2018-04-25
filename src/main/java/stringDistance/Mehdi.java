package stringDistance;

import encoder.DenoisingAutoEncoder;
import vectorDistance.VectorDistance;

public class Mehdi implements StringDistance {

    private DenoisingAutoEncoder encoder;
    private VectorDistance vectorDistance;

    public Mehdi (){
        super();
    }

    public Mehdi (DenoisingAutoEncoder encoder, VectorDistance distance){
        this.encoder=encoder;
        this.vectorDistance=distance;

    }

    public double distance(String a, String b) {
        return this.vectorDistance.distance(this.encoder.encode(a),this.encoder.encode(b));
    }
}
