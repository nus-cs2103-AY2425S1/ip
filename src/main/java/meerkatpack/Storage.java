package meerkatpack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Used to write to and read from saved txt file during the running
 * of the application.
 */
public class Storage {

    /**
     * Writes to the updated taskList whenever changes are made.
     * @param filePath The destination of file to write to.
     * @param textToAdd The entire taskList in the form of the parseable string.
     * @throws IOException When the creation of new file fails.
     */
    public void writeToFile(String filePath, String textToAdd) throws IOException {
        File f = new File(filePath);
        if (!f.exists()) {
            f.createNewFile();
        }
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Reads from the file specified in the filePath whenever program starts.
     * @param filePath The destination of the file to read from.
     * @throws FileNotFoundException When the file does not exist.
     */
    public void readFromFile(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            String thisTask = sc.nextLine();
            // reads the save file, loads taskList in parser with data
            Parser.parseSaveFile(thisTask);
        }
    }
}
