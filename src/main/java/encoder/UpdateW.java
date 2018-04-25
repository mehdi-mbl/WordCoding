package encoder;

import java.util.List;

public class UpdateW implements Runnable {

    private Thread updateWthread;
    private DenoisingAutoEncoder encoder;
    private int start;
    private int BatchSize;
    private List<String> input;
    private List<String> output;
    private List<String> c;
    double[][] g;

    public UpdateW(DenoisingAutoEncoder encoder, int start, int batchSize, List<String> input, List<String> output, List<String> c) {
         this.encoder = encoder;
        this.start = start;
        this.BatchSize = batchSize;
        this.input = input;
        this.output = output;
        this.c=c;
    }

    public void run() {
        this.g= new double[this.encoder.getW().length][this.encoder.getW()[0].length];
        this.g=MatrixOperation.intialize(this.g, 0);
        for (int i=this.start; i<this.start+this.BatchSize && i<this.input.size(); i++){
            this.g=MatrixOperation.add(g, this.encoder.deltaW(this.input.get(i),this.output.get(i),this.c));
            //this.g=MatrixOperation.add(g, this.encoder.deltaW(this.input.get(i),this.output.get(i)));
        }
    }

    public void start(){
        if (updateWthread==null){
            this.updateWthread = new Thread(this,"update B thread");
            this.updateWthread.start();
        }
    }

    public Thread getUpdateWthread() {
        return updateWthread;
    }

    public double[][] getG() {
        return this.g;
    }
}
