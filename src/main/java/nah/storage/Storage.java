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
     * Clears all text in the files to add new text.
     *
     * @param filePath the path to the file
     * @param textToAdd the text that we will add
     * @throws NahException if something wrong with the filePath
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
     * Return a LinkList of tasks extracted from the file at filePath (helper method).
     *
     * @return a LinkList object
     * @throws NahException if something wrong with the filePath
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
     * Rewrites the file with String s
     *
     * @param s the String that will be added
     * @throws NahException if something wrong with the filePath of this storage.
     */
    public void rewrite(String s) throws NahException {
        writeToFile(filePath, s);
    }

    /**
     * Clears all text in the file in filePath.
     *
     * @throws NahException
     */
    public void clean() throws NahException{
        try {
            FileWriter fw = new FileWriter(filePath, false);
            fw.close();
        } catch (IOException e) {
            throw new NahException(" Nahh!!! Something is wrong with the filePath");
        }
    }
}
