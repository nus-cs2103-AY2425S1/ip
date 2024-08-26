package ipman;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Storage handler (static file)
 * @author miloaisdino
 */
public class Storage {
    private final File file;

    /**
     * Constructor to use temp file
     */
    public Storage() {
        file = new File(String.valueOf("tmp_" + System.currentTimeMillis() + ".txt"));
        try {
            file.createNewFile();
        } catch (IOException e) {
            //do nothing
        }
    }

    /**
     * Constructor to use or create a specified file
     * @param filePath Specified file
     */
    public Storage(String filePath) {
        try {
            file = new File(filePath);
            if (!file.isFile()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Add a new row to the storage medium
     * @param str Data
     */
    public void addEntry(String str) {
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write(str);
            fw.write("\r\n");
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns scanner of the file database (needs to be closed manually)
     * @return Scanner
     */
    public Scanner getFileScanner() {
        try {
            return new Scanner(file);
        } catch (FileNotFoundException e) {
            return null;
        }
    }
}
