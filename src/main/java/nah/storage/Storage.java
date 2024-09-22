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

/**
 * Handles the storing the data into a hard disk.
 */
public class Storage {
    private static final String HARD_DISK =
            Paths.get("./Nah.txt").toString();
    private String filePath;
    public Storage() {
        this.filePath = HARD_DISK;
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
     * Creates a new hard disk if it doesn't exist.
     */
    private void createNewHardDisk() throws NahException {
        assert this.HARD_DISK.length() != 0 : "Nah!!! No path specified for our hard disk";
        File newFile = new File(HARD_DISK);

        // Creates a new file for caching data.
        try {
            newFile.createNewFile();
        } catch (IOException e) {
            throw new NahException("Nah!!! Hard disk file cannot be created");
        }
    }

    /**
     * Return a LinkList of tasks extracted from the file at filePath (helper method).
     *
     * @return a LinkList object
     * @throws NahException if something wrong with the filePath
     */
    public LinkedList<Task> load() throws NahException {
        LinkedList<Task> taskList = new LinkedList<Task>();
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                taskList.add(Decoder.decode(s.nextLine()));
            }
            s.close();
        } catch (FileNotFoundException e) {
            // file doesn't exist
            this.createNewHardDisk();
        }
        return taskList;

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
    public void clean() throws NahException {
        try {
            FileWriter fw = new FileWriter(filePath, false);
            fw.close();
        } catch (IOException e) {
            throw new NahException(" Nahh!!! Something is wrong with the filePath");
        }
    }
}
