package encoder;

import java.util.List;

public class UpdateWstar implements Runnable {

    private Thread updateWstarthread;
    private DenoisingAutoEncoder encoder;
    private int start;
    private int BatchSize;
    private List<String> input;
    private List<String> output;
    private List<String> c;
    double[][] g;

    public UpdateWstar(DenoisingAutoEncoder encoder, int start, int batchSize, List<String> input, List<String> output, List<String> c) {
        this.encoder = encoder;
        this.start = start;
        this.BatchSize = batchSize;
        this.input = input;
        this.output = output;
        this.c=c;
    }

    public void run() {
        this.g= new double[this.encoder.getWstar().length][this.encoder.getWstar()[0].length];
        this.g=MatrixOperation.intialize(g, 0);
        for (int i=this.start; i<this.start+this.BatchSize && i<this.input.size(); i++){
            this.g=MatrixOperation.add(g, this.encoder.deltaWstar(this.input.get(i),this.output.get(i),c));
            //this.g=MatrixOperation.add(g, this.encoder.deltaWstar(this.input.get(i),this.output.get(i)));
        }
    }

    public void start(){
        if (updateWstarthread==null){
            this.updateWstarthread = new Thread(this,"update B thread");
            this.updateWstarthread.start();
        }
    }

    public Thread getUpdateWstarthread() {
        return updateWstarthread;
    }

    public double[][] getG() {
        return g;
    }
}
