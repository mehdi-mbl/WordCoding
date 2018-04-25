package preprocessing;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

public class LoadFile {

    public Scanner read(String path) throws FileNotFoundException{
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        URL url= classLoader.getResource(path);
        System.out.println(url);
        System.out.println(url.getFile());
        File file = new File(url.getFile());
        System.out.println(file.exists());
        Scanner reader = new Scanner (file);
        return reader;
    }
}
