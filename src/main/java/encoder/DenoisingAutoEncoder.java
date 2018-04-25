package encoder;

import activations.ActivationFunction;
import vectorDistance.VectorDistance;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

public class DenoisingAutoEncoder implements Encoder, Serializable {
	
	private double [] b;
	private double [] c;
	private double [][] W;
	private double [][] Wstar;
	private int nbr_input;
	private int nbr_hidden;
	private VectorDistance distance;
	private Encoder initializer;
	private ActivationFunction activation;
	
	public DenoisingAutoEncoder (int nbrinput, int nbrhidden, VectorDistance distance, Encoder initializer, ActivationFunction activation){
		this.nbr_hidden = nbrhidden;
		this.nbr_input = nbrinput;
		this.b = new double [nbrhidden];
		this.c = new double [nbrinput];
		this.W = new double [nbrhidden][nbrinput];
		this.Wstar = new double [nbrinput][nbrhidden];
		initialize_hyperparameters("XAVIER");
		this.distance=distance;
		this.initializer=initializer;
		this.activation=activation;
	}
	
	public void initialize_hyperparameters (String method){
		if (method=="XAVIER"){
			for (int i =0; i<this.b.length; i++) b[i]=0;
			for (int i =0; i<this.c.length; i++) c[i]=0;
			for (int i =0; i<this.W.length; i++){
				for (int j =0; j<this.W[i].length; j++){
					Random r= new Random();
					this.W[i][j]=2*r.nextGaussian()/(this.nbr_hidden+this.nbr_input);
				}
			}
			for (int i =0; i<this.Wstar.length; i++){
				for (int j =0; j<this.Wstar[i].length; j++){
					Random r= new Random();
					this.Wstar[i][j]=2*r.nextGaussian()/(this.nbr_hidden+this.nbr_input);
				}
			}
		}
	}
	
	public double [] g(String input){
		double [] a = new double [this.nbr_hidden];
		for (int i =0; i<a.length; i++){
			a[i]=activation.activate(this.b[i]+MatrixOperation.multiply(this.W, this.initializer.encode(input))[i]);
			//1/(1+Math.exp(-this.b[i]-MatrixOperation.multiply(this.W, this.initializer.encode(input))[i]));
		}
		return a;
	}
	
	public double[] o(String input){
		double[] output = new double[nbr_input];
		for (int i =0; i<output.length; i++){
			output[i]= activation.activate(this.c[i]+MatrixOperation.multiply(this.Wstar, g(input))[i]);
			//1/(1+Math.exp(-this.c[i]-MatrixOperation.multiply(this.Wstar, g(input))[i]));
		}
		return output;
	}

	public double [] gPrime(String input){
		double [] a = new double [this.nbr_hidden];
		for (int i =0; i<a.length; i++){
			a[i]=activation.derivate(this.b[i]+MatrixOperation.multiply(this.W, this.initializer.encode(input))[i]);
			//1/(1+Math.exp(-this.b[i]-MatrixOperation.multiply(this.W, this.initializer.encode(input))[i]));
		}
		return a;
	}

	public double[] oPrime(String input){
		double[] output = new double[nbr_input];
		for (int i =0; i<output.length; i++){
			output[i]= activation.derivate(this.c[i]+MatrixOperation.multiply(this.Wstar, g(input))[i]);
			//1/(1+Math.exp(-this.c[i]-MatrixOperation.multiply(this.Wstar, g(input))[i]));
		}
		return output;
	}

	public double[] encode(String word) {
		return g(word);
	}

	public double Loss(String input, String expected){
		return this.distance.distance(o(input),this.initializer.encode(expected))/2;
	}

    public double Loss(String input, String expected, List<String> c){
        double r=0;
        for (int i=0;i<c.size();i++){
            r+=this.distance.distance(o(input),this.initializer.encode(c.get(i)));
        }
        r=r/(c.size());
        return (this.distance.distance(o(input),this.initializer.encode(expected))-r)/2;
    }

    public double[][] deltaW(String input, String expected, List<String> c){
		double [][] delta = new double [this.W.length][this.W[0].length];
		double [][] temp = new double [this.W.length][this.W[0].length];
		MatrixOperation.intialize(temp,0);
		for (int k=0;k<c.size(); k++){
			for (int i =0; i<this.W.length; i++){
				for (int j =0; j<this.W[i].length; j++){
					temp[i][j]+= MatrixOperation.multiply(this.distance.derivate(o(input),this.initializer.encode(expected)),
							MatrixOperation.dotmultiply( oPrime(input),
									MatrixOperation.multiply( this.Wstar, gPrime(input))))*this.initializer.encode(input)[j];
				}
			}
		}

        for (int i =0; i<this.W.length; i++){
            for (int j =0; j<this.W[i].length; j++){
                delta[i][j]= MatrixOperation.multiply(this.distance.derivate(o(input), this.initializer.encode(expected)),
                        MatrixOperation.dotmultiply( oPrime(input),
                                MatrixOperation.multiply( this.Wstar, gPrime(input))))*this.initializer.encode(input)[j];
            }
        }
		System.out.println("deltaW finished");
        return MatrixOperation.add(delta,MatrixOperation.multiply(temp,-1/c.size()));
    }

    public double[][] deltaWstar(String input, String expected, List<String> c){
        double [][] delta = new double [this.Wstar.length][this.Wstar[0].length];
        double [][] temp = new double [this.Wstar.length][this.Wstar[0].length];
        MatrixOperation.intialize(temp,0);
        for (int k=0;k<c.size(); k++){
			for (int i =0; i<this.Wstar.length; i++){
				for (int j =0; j<this.Wstar[i].length; j++){
					temp[i][j]+= MatrixOperation.multiply(this.distance.derivate(o(input),this.initializer.encode(expected)),
							oPrime(input))*g(input)[j];
				}
			}
		}
        for (int i =0; i<this.Wstar.length; i++){
            for (int j =0; j<this.Wstar[i].length; j++){
                delta[i][j]= MatrixOperation.multiply(this.distance.derivate(o(input), this.initializer.encode(expected)),
                        oPrime(input))*g(input)[j];
            }
        }
        return MatrixOperation.add(delta,MatrixOperation.multiply(temp,-1/c.size()));
    }

    public double[] deltab(String input, String expected, List<String> c){
        double [] delta = new double [this.b.length];
		double [] temp = new double [this.b.length];
		MatrixOperation.intialize(temp,0);
		for (int k=0;k<c.size(); k++){
			for (int i =0; i<this.b.length; i++){
				temp[i]+=MatrixOperation.multiply(this.distance.derivate(o(input),this.initializer.encode(expected)),
						MatrixOperation.dotmultiply( oPrime(input),
								MatrixOperation.multiply(this.Wstar, gPrime(input))));
			}
		}

        for (int i =0; i<this.b.length; i++){
            delta[i]= MatrixOperation.multiply(this.distance.derivate(o(input), this.initializer.encode(expected)),
                    MatrixOperation.dotmultiply( oPrime(input),
                            MatrixOperation.multiply(this.Wstar, gPrime(input))));
        }
        return MatrixOperation.add(delta,MatrixOperation.multiply(temp,-1/c.size()));
    }

    public double[] deltac(String input, String expected, List<String> c){
        double [] delta = new double [this.c.length];
		double [] temp = new double [this.c.length];
		MatrixOperation.intialize(temp,0);
		for (int k=0;k<c.size(); k++){
			for (int i =0; i<this.c.length; i++){
				temp[i]+= MatrixOperation.multiply(this.distance.derivate(o(input),this.initializer.encode(expected)), oPrime(input));
			}
		}

        for (int i =0; i<this.c.length; i++){
            delta[i]= MatrixOperation.multiply(this.distance.derivate(o(input), this.initializer.encode(expected)), oPrime(input));
        }
        return MatrixOperation.add(delta,MatrixOperation.multiply(temp,-1/c.size()));
    }
	
	public double[][] deltaW(String input, String expected){
		double [][] delta = new double [this.W.length][this.W[0].length];
		for (int i =0; i<this.W.length; i++){
			for (int j =0; j<this.W[i].length; j++){
				delta[i][j]= MatrixOperation.multiply(this.distance.derivate(o(input), this.initializer.encode(expected)),
						MatrixOperation.dotmultiply( oPrime(input),
								MatrixOperation.multiply( this.Wstar, gPrime(input))))*this.initializer.encode(input)[j];
			}
		}
		return delta;
	}
	
	public double[][] deltaWstar(String input, String expected){
		double [][] delta = new double [this.Wstar.length][this.Wstar[0].length];
		for (int i =0; i<this.Wstar.length; i++){
			for (int j =0; j<this.Wstar[i].length; j++){
				delta[i][j]= MatrixOperation.multiply(this.distance.derivate(o(input), this.initializer.encode(expected)),
						oPrime(input))*g(input)[j];
			}
		}
		return delta;
	}
	
	public double[] deltab(String input, String expected){
		double [] delta = new double [this.b.length];
		for (int i =0; i<this.b.length; i++){
			delta[i]= MatrixOperation.multiply(this.distance.derivate(o(input), this.initializer.encode(expected)),
					MatrixOperation.dotmultiply( oPrime(input),
							MatrixOperation.multiply(this.Wstar, gPrime(input))));
		}
		return delta;
	}
	
	public double[] deltac(String input, String expected){
		double [] delta = new double [this.c.length];
		for (int i =0; i<this.c.length; i++){
			delta[i]= MatrixOperation.multiply(this.distance.derivate(o(input), this.initializer.encode(expected)), oPrime(input));
		}
		return delta;
	}


	public double[] getB() {
		return b;
	}

	public void setB(double[] b) {
		this.b = b;
	}

	public double[] getC() {
		return c;
	}

	public void setC(double[] c) {
		this.c = c;
	}

	public double[][] getW() {
		return W;
	}

	public void setW(double[][] w) {
		W = w;
	}

	public double[][] getWstar() {
		return Wstar;
	}

	public void setWstar(double[][] wstar) {
		Wstar = wstar;
	}

}
