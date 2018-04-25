package encoder;

import java.util.List;

public class UpdateB implements Runnable {

    private Thread updateBthread;
    private DenoisingAutoEncoder encoder;
    private int start;
    private int BatchSize;
    private List<String> input;
    private List<String> output;
    private List<String> c;
    double[][] g;

    public UpdateB(DenoisingAutoEncoder encoder, int start, int batchSize, List<String> input, List<String> output, List<String> c) {
        this.encoder = encoder;
        this.start = start;
        this.BatchSize = batchSize;
        this.input = input;
        this.output = output;
        this.c=c;
    }

    public void run() {
        this.g= new double[1][this.encoder.getB().length];
        this.g=MatrixOperation.intialize(g, 0);
        for (int i=this.start; i<this.start+this.BatchSize && i<this.input.size(); i++){
            this.g[0]=MatrixOperation.add(g[0], this.encoder.deltab(this.input.get(i),this.output.get(i),this.c));
            //this.g[0]=MatrixOperation.add(g[0], this.encoder.deltab(this.input.get(i),this.output.get(i)));
        }
    }

    public void start(){
        if (updateBthread==null){
            this.updateBthread = new Thread(this,"update B thread");
            this.updateBthread.start();
        }
    }

    public Thread getUpdateBthread() {
        return updateBthread;
    }

    public double[][] getG() {
        return this.g;
    }
}
