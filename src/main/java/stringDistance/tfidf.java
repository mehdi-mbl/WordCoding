package stringDistance;


import com.aliasi.spell.TfIdfDistance;
import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory;
import com.aliasi.tokenizer.TokenizerFactory;

public class tfidf implements StringDistance {


    public double distance(String a, String b) {
        TokenizerFactory tokenizerFactory = IndoEuropeanTokenizerFactory.INSTANCE;
        TfIdfDistance tfIdfDistance = new TfIdfDistance(tokenizerFactory);
        return tfIdfDistance.distance(a,b);
    }
}
