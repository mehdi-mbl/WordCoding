package encoder;

import java.util.ArrayList;

public class DynamicTimeWarpping implements Encoder {

    private String X;
    private String Y;
    private double[][] DXY;
    private ArrayList<int[]> Pstar;

    public DynamicTimeWarpping (String y){
        this.Y=y;
        this.Pstar = new ArrayList<int[]>();
        this.DXY= new double[this.X.length()][this.Y.length()];
        for (int i=0; i<this.DXY.length; i++){
            for (int j=0; j<this.DXY[i].length; j++){
                this.DXY[i][j]=distance(this.X.charAt(i), this.Y.charAt(j));
            }
        }
    }

    public double distance(char a, char b){
        if (a==b) return 0;
        else return 1;
    }

    public ArrayList<int[]> wrappingPath(int n, int m){
        ArrayList<int[]> pstar = new ArrayList<int[]>();
        pstar.add(new int[] {n, m});
        if (n==0 && m==0){
            pstar.add(new int[] {0, 0});
        }
        else if (n==0) {
            pstar.addAll(wrappingPath (0, m-1));
        }
        else if (m==0) {
            pstar.addAll(wrappingPath(n-1, 0));
        }
        else {
            int [] min=argmin(new int[] {n-1, m-1}, new int[] {n-1,m}, new int[] {n,m-1});
            pstar.addAll(wrappingPath(min[0], min[1]));
        }
        return pstar;
    }

    public int[] argmin(int[] d1, int[] d2, int[] d3){
        int[] p= new int[2];
        double [] c = new double[] {D(wrappingPath(d1[0], d1[1])), D(wrappingPath(d2[0], d2[1])), D(wrappingPath(d3[0], d3[1]))};
        double min = c[0];
        int index=1;
        for (int i=1; i<c.length; i++){
            if (c[i]<min) {
                min = c[i];
                index=i+1;
            }
        }
        if (index==1) p= d1;
        else if (index==2) p=d2;
        else if (index==3) p=d3;
        return p;
    }

    public double D(ArrayList<int[]> d){
        double cc=0;
        for (int i=0; i<d.size(); i++){
            cc+=this.DXY[d.get(i)[0]][d.get(i)[1]];
        }
        return cc;
    }

    public double[] encode(String word) {
        this.X=word;
        this.Pstar=wrappingPath (this.X.length()-1, this.Y.length()-1);
        char[] wordalg= new char[this.Y.length()];
        wordalg[0]= word.charAt(0);
        for (int i=1; i<this.Pstar.size();i++){
            if (this.Pstar.get(i)[0]>this.Pstar.get(i-1)[0] && this.Pstar.get(i)[1]>this.Pstar.get(i-1)[1])
                wordalg[i]=word.charAt(i);
            else if (this.Pstar.get(i)[0]>this.Pstar.get(i-1)[0] && this.Pstar.get(i)[1]==this.Pstar.get(i-1)[1])
                wordalg[i]='$';
        }
        Vector vector= new Vector();
        return  vector.encode(new String(wordalg));
    }
}
