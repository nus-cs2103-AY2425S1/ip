package Storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import Data.Task;
import Exceptions.*;

public class Storage {
    private String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private static void writeToFile(String filePath, String textToAdd) throws NahException{
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            throw new NahException(" Nahh!!! Something is wrong with the filePath");
        }
    }


    /**
     * Return a Link list of tasks extracted from the file at filePath
     * @return
     * @throws FileNotFoundException
     * @throws InvalidFileValueException
     */
    public LinkedList<Task> load() throws NahException{
        LinkedList<Task> t = new LinkedList<Task>();
        File f;
        Scanner s;
        try {
            f = new File(filePath);
            s = new Scanner(f   );
        } catch (FileNotFoundException | NullPointerException e){
            throw new NahException(" Nahh!!! Something is wrong with the filePath");
        }

        while (s.hasNext()) {
            t.add(Decoder.decode(s.nextLine()));
        }

        return t;

    }

    /**
     * rewrite the file with String s
     * @param s
     */
    public void rewrite(String s) throws NahException {
        writeToFile(filePath, s);
    }
}
