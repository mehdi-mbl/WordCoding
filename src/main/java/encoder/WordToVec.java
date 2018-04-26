package encoder;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.text.sentenceiterator.BasicLineIterator;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.nd4j.linalg.io.ClassPathResource;
import org.slf4j.Logger;
import org.slf4j.helpers.SubstituteLogger;

import java.util.Collection;


public class WordToVec {

    private SentenceIterator iter;
    private Word2Vec vec;
    private Logger log;

    public WordToVec(){
        this.log=new SubstituteLogger("word 2 vec logger");
    }

    public void loadData(String file) throws Exception{
        String filePath = new ClassPathResource(file).getFile().getAbsolutePath();

        this.log.info("Load & Vectorize Sentences....");
        System.out.println("Load & Vectorize Sentences....");
        // Strip white space before and after for each line
        this.iter = new BasicLineIterator(filePath);
    }

    public void train()  {

        // Split on white spaces in the line to get words
        TokenizerFactory t = new DefaultTokenizerFactory();
        t.setTokenPreProcessor(new CommonPreprocessor());
        System.out.println("Building model....");
        this.log.info("Building model....");
        this.vec = new Word2Vec.Builder()
                .minWordFrequency(1)
                .iterations(300)
                .layerSize(36*8)
                .seed(42)
                .windowSize(5)
                .iterate(iter)
                .tokenizerFactory(t)
                .build();

        this.log.info("Fitting model....");
        System.out.println("Fitting model....");
        this.vec.fit();

    }

    public void writeResults(String file) {
        // Write word vectors
        WordVectorSerializer.writeWord2VecModel(this.vec, file);

    }

}
