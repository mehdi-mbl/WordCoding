package encoder;

public class MatrixOperation  {
	
	public static double[][] intialize (double [][] x, double d){
		double[][] g= new double[x.length][x[0].length];
		for (int i=0; i<g.length; i++){
			for (int j=0; j<g[i].length; j++){
				g[i][j]=d;
			}
		}
		return g;
	}
	
	public static double[] intialize (double [] x, double d){
		double[] g= new double[x.length];
		for (int i=0; i<g.length; i++) g[i]=d;
		return g;
	}



	public static double[][] multiply(double [][] M , double x){
		double[][] v= new double [M.length][M[0].length];
		//System.out.println(M.length +" "+ M[0].length +" "+ x.length);
		for (int i=0; i<M.length; i++){
			for (int j=0; j<M[i].length; j++){
				v[i][j]=M[i][j]*x;
			}
		}
		return v;
	}

	public static double[] multiply(double [] M , double x){
		double[] v= new double [M.length];
		//System.out.println(M.length +" "+ M[0].length +" "+ x.length);
		for (int i=0; i<M.length; i++){
			v[i]=M[i]*x;
		}
		return v;
	}

	public static double[] multiply(double [][] M , double [] x){
		double[] v= new double [M.length];
		//System.out.println(M.length +" "+ M[0].length +" "+ x.length);
		if (x.length != M[0].length) {
			System.out.println("MatrixOperation multiply M X: Matrix dimensions do not much");
		} else{
			for (int i=0; i<M.length; i++){
				for (int j=0; j<M[i].length; j++){
					v[i]+=M[i][j]*x[j];
				}
			}
		}
		return v;
	}
	
	public static double multiply(double [] x, double [] y){
		double v= 0;
		if (x.length != y.length) {
			System.out.println("MatrixOperation multiply x y: Matrix dimensions do not much");
		}else{
			for (int i=0; i<x.length; i++) v+=x[i]*y[i];
		}
		return v;
	}
	
	public static double[] dotmultiply(double [] x, double [] y){
		double[] v= new double[x.length];
		if (x.length != y.length) {
			System.out.println("MatrixOperation dotmultiply: Matrix dimensions do not much");
		}else{
			for (int i=0; i<x.length; i++) v[i]=x[i]*y[i];
		}
		return v;
	}
	
	public static double[] add(double [] x, double [] y){
		double[] v= new double [x.length];
		if (x.length != y.length) {
			System.out.println("MatrixOperation add x y: Matrix dimensions do not much");
		}else{
			for (int i=0; i<x.length; i++) v[i]=x[i]+y[i];
		}
		return v;
	}
	
	public static double[][] add(double [][] x, double [][] y){
		double[][] v= new double [x.length][x[0].length];
		if (x.length != y.length) {
			System.out.println("MatrixOperation add M1 m2: Matrix dimensions do not much");
		}else{
			for (int i=0; i<x.length; i++) {
				for (int j=0; j<x[i].length; j++) {
					v[i][j]=x[i][j]+y[i][j];
				}
			}
		}
		return v;
	}
	
	public static double[] substruct (double [] x, double [] y){
		double[] v= new double [x.length];
		if (x.length != y.length) {
			System.out.println("MatrixOperation substruct: Matrix dimensions do not much");
		}else{
			for (int i=0; i<x.length; i++) v[i]=x[i]-y[i];
		}
		return v;
	}
	
	public static double[][] transpose(double[][] M){
		double[][] v= new double [M[0].length][M.length];
		for (int i=0; i<M.length; i++){
			for (int j=0; j<M[i].length; j++){
				v[j][i]=M[i][j];	
			}
		}
		return v;
	}

	public static boolean isEqual(double[] a, double[] b) {
		if (a.length != b.length) {
			System.out.println("MatrixOperation substruct: Matrix dimensions do not much");
		} else {
			for (int i = 0; i < a.length; i++) {
				if (a[i]!=b[i]) return false;
			}
		}
		return true;
	}
}
