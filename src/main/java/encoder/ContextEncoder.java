package encoder;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;

import java.io.Serializable;

public class ContextEncoder  implements Encoder, Serializable {

    private Word2Vec vec;

    public ContextEncoder(String file){
        vec= WordVectorSerializer.readWord2VecModel(file);
    }

    public double[] encode(String word) {
        double[] v = new double[100];
        Vector vector = new Vector(100);
        if (this.vec.getWordVector(word)!=null) return this.vec.getWordVector(word);
        else return vector.encode(word);
    }
}
