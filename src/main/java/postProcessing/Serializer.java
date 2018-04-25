package postprocessing;

import encoder.DenoisingAutoEncoder;

import java.io.*;

public class Serializer {

    public void serialize (DenoisingAutoEncoder e, String file) throws IOException{
        FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(e);
        out.close();
        fileOut.close();

    }

    public DenoisingAutoEncoder deserialize(String file) throws Exception{
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        DenoisingAutoEncoder e = (DenoisingAutoEncoder) in.readObject();
        in.close();
        fileIn.close();
        return e;

    }

}
