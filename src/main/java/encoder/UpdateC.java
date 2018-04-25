package encoder;

import java.util.List;

public class UpdateC implements Runnable {

    private Thread updateCthread;
    private DenoisingAutoEncoder encoder;
    private int start;
    private int BatchSize;
    private List<String> input;
    private List<String> output;
    private List<String> c;
    double[][] g;

    public UpdateC(DenoisingAutoEncoder encoder, int start, int batchSize, List<String> input, List<String> output, List<String> c) {
        this.encoder = encoder;
        this.start = start;
        this.BatchSize = batchSize;
        this.input = input;
        this.output = output;
        this.c=c;
    }

    public void run() {
        this.g= new double[1][this.encoder.getC().length];
        this.g=MatrixOperation.intialize(g, 0);
        for (int i=this.start; i<this.start+this.BatchSize && i<this.input.size(); i++){
            //this.g[0]=MatrixOperation.add(g[0], this.encoder.deltac(this.input.get(i),this.output.get(i)));
            this.g[0]=MatrixOperation.add(g[0], this.encoder.deltac(this.input.get(i),this.output.get(i),this.c));
        }
    }

    public void start(){
        if (updateCthread==null){
            this.updateCthread = new Thread(this,"update B thread");
            this.updateCthread.start();
        }
    }

    public Thread getUpdateCthread() {
        return updateCthread;
    }

    public double[][] getG() {
        return this.g;
    }
}
