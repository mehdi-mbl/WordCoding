package encoder;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;

import java.io.Serializable;

public class ContextEncoder  implements Encoder, Serializable {

    private Word2Vec vec;
    private Encoder fallDown;

    public ContextEncoder(String file, Encoder fallDown) {
        this.vec = WordVectorSerializer.readWord2VecModel(file);
        this.fallDown = fallDown;
    }

    public ContextEncoder(String file){
        vec= WordVectorSerializer.readWord2VecModel(file);
    }

    public double[] encode(String word) {
        if (this.vec.getWordVector(word)!=null) return this.vec.getWordVector(word);
        else return fallDown.encode(word);
    }
}
