package nah.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Scanner;
import nah.data.Task;
import nah.exceptions.NahException;

public class Storage {
    private String filePath;


    private static final String hardDisk =
            Paths.get("D:", "cs2103T_week_2", "Data", "Nah.Nah.txt").toString();

    public Storage() {
        this.filePath = hardDisk;
    }
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Write to a file
     * @param filePath
     * @param textToAdd
     * @throws NahException
     */
    private static void writeToFile(String filePath, String textToAdd) throws NahException {
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
     * @throws NahException
     */
    public LinkedList<Task> load() throws NahException {
        LinkedList<Task> t = new LinkedList<Task>();
        File f;
        Scanner s;
        try {
            f = new File(filePath);
            s = new Scanner(f   );
        } catch (FileNotFoundException | NullPointerException e) {
            throw new NahException(" Nahh!!! Something is wrong with the filePath");
        }

        while (s.hasNext()) {
            t.add(Decoder.decode(s.nextLine()));
        }
        s.close();
        return t;

    }

    /**
     * rewrite the file with String s
     * @param s
     */
    public void rewrite(String s) throws NahException {
        writeToFile(filePath, s);
    }

    public void clean() throws NahException{
        try {
            FileWriter fw = new FileWriter(filePath, false);
            fw.close();
        } catch (IOException e) {
            throw new NahException(" Nahh!!! Something is wrong with the filePath");
        }
    }
}
