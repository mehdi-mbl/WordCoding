package preprocessing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class NoisyWordCreator {
	
	private int chracters=126;
    private int nbrchracters=18;
    private double meannbrchracters=1.6;
    private double stdnbrchracters=0.8;
    private int numberofnoisywords=30;
    private ArrayList<int[]> data;
    private ArrayList<int[]> noisydata;
    private ArrayList<String> labels;

    public NoisyWordCreator () {
        String splitBy = "\t";
        BufferedReader br;
        data = new ArrayList<int[]>();
        noisydata = new ArrayList<int[]>();
        labels =new ArrayList<String>();
        try {
            br = new BufferedReader(new FileReader("trainCorrect"));
            String line;
            while(( line = br.readLine()) !=null){
                String[] b = line.split(splitBy);
                int[] datapoint = new int[b.length];
                for (int j=0; j<datapoint.length-1; j++){
                    datapoint[j]=Integer.parseInt(b[j]);
                }
                data.add(datapoint);
                labels.add(b[datapoint.length-1]);
            }
            br.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (int i=0; i<data.size(); i++){
            for (int j=0; j<numberofnoisywords; j++){
                int [] nword= addnoise(data.get(i), labels.get(i).length());
                int[] tab = new int[nword.length*2];
                for (int k=0; k<nword.length;k++){
                    tab[k]=nword[k];
                }
                for (int k=nword.length; k<tab.length;k++){
                    tab[k]=data.get(i)[k-nword.length];
                }
                noisydata.add(tab);
            }
        }

        Collections.shuffle(noisydata);

        PrintWriter writer;
        try {
            writer = new PrintWriter("noisytrainCorrect", "UTF-8");
            for (int i = 0; i< noisydata.size(); i++){
                for (int j=0; j<noisydata.get(i).length; j++){
                    writer.print(noisydata.get(i)[j]);
                    writer.print(";");
                }
                writer.println();
            }
            writer.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public int[] addnoise(int[] word, int length){
        int[] nword = new int [word.length];
        for (int i=0;i<word.length;i++){
            nword[i]=word[i];
        }
        int nbrchracterstoedit=gaussian(meannbrchracters,stdnbrchracters);
        int chracterstoedit;
        int chracterreplacemet;
        for (int i=0;i<nbrchracterstoedit;i++){
            nbrchracters=length;
            chracterstoedit=uniform(0,nbrchracters-1);
            int operation=uniform(0,2);
            if (operation==0){
                boolean replaced=false;
                while(!replaced){
                    chracterreplacemet=uniform(32,chracters);
                    if (nword[chracterstoedit]!=chracterreplacemet){
                        nword[chracterstoedit]=chracterreplacemet;
                        replaced=true;
                    }
                }
            } else if(operation==1){
                for (int k=chracterstoedit;k<nbrchracters-1;k++){
                    nword[k]=nword[k+1];
                }
                nword[nbrchracters-1]=0;
                nbrchracters--;
            }else {
                for (int k=nbrchracters-1;k>chracterstoedit;k--){
                    nword[k]=nword[k-1];
                }
                nword[chracterstoedit]=uniform(0,chracters);
                nbrchracters++;
            }
        }
        return nword;
    }

    public int uniform (int min, int max){
        return (int) ((max-min+1)*Math.random()+min);
    }

    public int gaussian (double mean, double variance){
        Random r = new Random();
        return (int) (variance*r.nextGaussian()+mean);
    }

}
