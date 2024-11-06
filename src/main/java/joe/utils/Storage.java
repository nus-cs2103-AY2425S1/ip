package joe.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Represents the storage of the tasks.
 */
public class Storage {

    private static final String LINE =
            "____________________________________________________________";
    private final File file;

    /**
     * Constructor for Storage.
     *
     * @param filePath the file path to retrieve and store the tasks
     */
    public Storage(String filePath) {
        file = new File(filePath);
    }

    /**
     * Loads the data from the file.
     *
     * @return a String array containing the data
     * @throws FileNotFoundException if the file is not found
     */
    public String[] load() throws FileNotFoundException {
        System.out.println(LINE);
        System.out.println("Attempting to sync your data......");
        Scanner sc = new Scanner(file);
        String[] lines = new String[100];
        int i = 0;
        while (sc.hasNextLine()) {
            lines[i] = sc.nextLine();
            i++;
        }
        return lines;
    }
}
