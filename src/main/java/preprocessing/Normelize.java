package preprocessing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Normelize {
	String splitBy = ";";
    BufferedReader br;
    PrintWriter writer;
    public Normelize(String file){
    try {
        br = new BufferedReader(new FileReader(file));
        writer = new PrintWriter("Normalized"+file, "UTF-8");
        String line;
        while(( line = br.readLine()) !=null){
            String[] b = line.split(splitBy);
            double[] datapoint = new double[b.length];
            //System.out.println(b[n]);
            for (int j=0; j<datapoint.length; j++){
                //System.out.print(" "+b[j]);
                datapoint[j]=Double.parseDouble(b[j]);
                datapoint[j]=(datapoint[j]-63)/63;
            }
            for (int j=0; j<datapoint.length; j++){
                writer.print(datapoint[j]);
                writer.print(";");
            }
            writer.println();
        }
        br.close();
        writer.close();
    } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}

}
