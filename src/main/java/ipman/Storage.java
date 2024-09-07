package ipman;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Handles storage operations for tasks.
 * This class provides methods to read from and write to a file.
 * It supports both temporary and specified file storage.
 *
 * @author miloaisdino
 */
public class Storage {
    private final File file;

    /**
     * Constructs a Storage instance using a temporary file.
     * The temporary file is created with a unique name based on the current timestamp.
     */
    public Storage() {
        file = new File(String.valueOf("tmp_" + System.currentTimeMillis() + ".txt"));
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Constructs a Storage instance using a specified file path.
     * If the file does not exist, it is created along with any necessary parent directories.
     *
     * @param filePath The path of the file to use for storage.
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
     * Adds a new entry to the storage file.
     * The entry is appended to the end of the file.
     *
     * @param str The data to be added to the file.
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
     * Returns a Scanner to read from the storage file.
     * The caller is responsible for closing the Scanner.
     *
     * @return A Scanner to read from the file.
     * @throws RuntimeException If the file is not found.
     */
    public Scanner getFileScanner() {
        try {
            return new Scanner(file);
        } catch (FileNotFoundException e) {
            return null;
        }
    }
}
