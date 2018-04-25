package encoder;

import java.util.List;

public class StochasticGradientDescent {
	
	private int BatchSize;
	private int iterations;
	private double alpha;
	private DenoisingAutoEncoder encoder;
	private List<String> input;
	private List<String> output;
	private List<String> c;
	
	
	public StochasticGradientDescent(DenoisingAutoEncoder encoder, int BatchSize, int iterations, double learningRate, List<String> input, List<String> output){
		this.BatchSize=BatchSize;
		this.alpha=learningRate;
		this.input=input;
		this.output=output;
		this.iterations=iterations;
		this.encoder = encoder;
	}

    public StochasticGradientDescent(DenoisingAutoEncoder encoder, int batchSize, int iterations, double learningRate, List<String> input, List<String> output, List<String> c) {
        BatchSize = batchSize;
        this.iterations = iterations;
        this.alpha = learningRate;
        this.input = input;
        this.output = output;
        this.c = c;
		this.encoder = encoder;
    }

    public void GradientDescent (){
		for(int i=0; i< this.iterations;i++){
			int j=0;
			while(j+this.BatchSize<= input.size()){
				this.encoder.setW(Descent(this.encoder.getW(), grad("W",j)));
				this.encoder.setWstar(Descent(this.encoder.getWstar(), grad("Wstar",j)));
				this.encoder.setB(Descent(this.encoder.getB(), grad("b",j)[0]));
				this.encoder.setC(Descent(this.encoder.getC(), grad("c",j)[0]));
				j+=this.BatchSize;
			}
			System.out.println("Loss after "+i+" iterations is: "+averageLoss());
		}
	}

    public void NewGradientDescent (){
        for(int i=0; i< this.iterations;i++){
            int j=0;
            while(j+this.BatchSize<= input.size()){

				System.out.println("Batch "+j);
                this.encoder.setW(Descent(this.encoder.getW(), gradNew("W",j)));
                this.encoder.setWstar(Descent(this.encoder.getWstar(), gradNew("Wstar",j)));
                this.encoder.setB(Descent(this.encoder.getB(), gradNew("b",j)[0]));
                this.encoder.setC(Descent(this.encoder.getC(), gradNew("c",j)[0]));
                j+=this.BatchSize;
            }
            System.out.println("Loss after "+i+" iterations is: "+averageNewLoss());
        }
    }

	public void MultithreadGradientDescent () throws Exception{
		for(int i=0; i< this.iterations;i++){
			int j=0;
			while(j+this.BatchSize<= input.size()){
				UpdateW updateW = new UpdateW(this.encoder, j, this.BatchSize, this.input, this.output, this.c);
				UpdateWstar updateWstar = new UpdateWstar(this.encoder, j, this.BatchSize, this.input, this.output, this.c);
				UpdateB updateB = new UpdateB(this.encoder, j, this.BatchSize, this.input, this.output, this.c);
				UpdateC updateC = new UpdateC(this.encoder, j, this.BatchSize, this.input, this.output, this.c);
				updateW.start();
				updateWstar.start();
				updateB.start();
				updateC.start();

				try {
					updateW.getUpdateWthread().join(1000);
					updateWstar.getUpdateWstarthread().join(1000);
					updateB.getUpdateBthread().join(1000);
					updateC.getUpdateCthread().join(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				this.encoder.setW(Descent(this.encoder.getW(), updateW.getG()));

				this.encoder.setWstar(Descent(this.encoder.getWstar(), updateWstar.getG()));

				this.encoder.setB(Descent(this.encoder.getB(), updateB.getG()[0]));

				this.encoder.setC(Descent(this.encoder.getC(), updateC.getG()[0]));
				j+=this.BatchSize;
			}
		}
	}
	
	public double[][] Descent(double[][] parameters, double[][] grad){
		double[][] UpdatedParameters= new double[parameters.length][parameters[0].length];
		for (int i=0; i<parameters.length; i++){
			for (int j=0; j<parameters[i].length; j++){
				UpdatedParameters[i][j]=parameters[i][j]-(this.alpha/this.BatchSize)*grad[i][j];
			}
		}
		return UpdatedParameters;
	}
	
	public double[] Descent(double[] parameters, double[] grad){
		double[] UpdatedParameters= new double[parameters.length];
		for (int i=0; i<parameters.length; i++){
			parameters[i]=parameters[i]-(this.alpha/this.BatchSize)*grad[i];
		}
		return UpdatedParameters;
	}

	public double[][] grad(String s, int start){
		double[][] g;
		if (s=="W"){
			g= new double[this.encoder.getW().length][this.encoder.getW()[0].length];
			g=MatrixOperation.intialize(g, 0);
			for (int i=start; i<start+this.BatchSize && i<this.input.size(); i++){
				this.encoder.o(input.get(i));
				g=MatrixOperation.add(g, this.encoder.deltaW(this.input.get(i),this.output.get(i)));
			}
		}else if (s=="Wstar"){
			g= new double[this.encoder.getWstar().length][this.encoder.getWstar()[0].length];
			g=MatrixOperation.intialize(g, 0);
			for (int i=start; i<start+this.BatchSize && i<this.input.size(); i++){
				this.encoder.o(input.get(i));
				g=MatrixOperation.add(g, this.encoder.deltaWstar(this.input.get(i),this.output.get(i)));
			}
		}else if (s=="b"){
			g= new double[1][this.encoder.getB().length];
			g=MatrixOperation.intialize(g, 0);
			for (int i=start; i<start+this.BatchSize && i<this.input.size(); i++){
				this.encoder.o(input.get(i));
				g[0]=MatrixOperation.add(g[0], this.encoder.deltab(this.input.get(i),this.output.get(i)));
			}
		}else {
			g= new double[1][this.encoder.getC().length];
			g=MatrixOperation.intialize(g, 0);
			for (int i=start; i<start+this.BatchSize && i<this.input.size(); i++){
				this.encoder.o(input.get(i));
				g[0]=MatrixOperation.add(g[0], this.encoder.deltac(this.input.get(i),this.output.get(i)));
			}
		}
		return g;
	}

    public double[][] gradNew(String s, int start){
        double[][] g;
        if (s=="W"){
            g= new double[this.encoder.getW().length][this.encoder.getW()[0].length];
            g=MatrixOperation.intialize(g, 0);
			for (int i=start; i<start+this.BatchSize && i<this.input.size(); i++){
				this.encoder.o(input.get(i));
				g=MatrixOperation.add(g, this.encoder.deltaW(this.input.get(i),this.output.get(i),c));
			}
        }else if (s=="Wstar"){
            g= new double[this.encoder.getWstar().length][this.encoder.getWstar()[0].length];
            g=MatrixOperation.intialize(g, 0);
			for (int i=start; i<start+this.BatchSize && i<this.input.size(); i++){
				this.encoder.o(input.get(i));
				g=MatrixOperation.add(g, this.encoder.deltaWstar(this.input.get(i),this.output.get(i),c));
			}
        }else if (s=="b"){
            g= new double[1][this.encoder.getB().length];
            g=MatrixOperation.intialize(g, 0);
			for (int i=start; i<start+this.BatchSize && i<this.input.size(); i++){
				this.encoder.o(input.get(i));
				g[0]=MatrixOperation.add(g[0], this.encoder.deltab(this.input.get(i),this.output.get(i),this.c));
			}
        }else {
            g= new double[1][this.encoder.getC().length];
            g=MatrixOperation.intialize(g, 0);
			for (int i=start; i<start+this.BatchSize && i<this.input.size(); i++){
				this.encoder.o(input.get(i));
				g[0]=MatrixOperation.add(g[0], this.encoder.deltac(this.input.get(i),this.output.get(i),this.c));
			}
        }
        return g;
    }
	
	public double averageLoss(){
		double loss=0;
		for (int i=0; i<input.size(); i++){
			loss+=this.encoder.Loss(output.get(i), input.get(i));
		}
		return loss/this.input.size();
	}

    public double averageNewLoss(){
        double loss=0;
        for (int i=0; i<input.size(); i++){
            loss+=this.encoder.Loss(output.get(i), input.get(i), this.c);
        }
        return loss/this.input.size();
    }


}
