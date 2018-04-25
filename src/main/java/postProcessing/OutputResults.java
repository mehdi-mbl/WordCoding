package postprocessing;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class OutputResults {
	
	private PrintWriter writer;
    
    public OutputResults(String filename){
    	try {
			this.writer = new PrintWriter(filename, "UTF-8");
		} catch (FileNotFoundException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e){
    		e.printStackTrace();
		}
    }
    
    public void write (double [] data){
        	for (int j=0; j<data.length; j++){
        		writer.print(data[j]);
        		if (j<data.length-1) writer.print(";");
        	}
        	writer.println();
    }

	public void write (double data){
		writer.print(data);
	}

	public void write (String data){
		writer.print(data);
	}

    
    public void close(){
    	writer.close();
    }
	
}
