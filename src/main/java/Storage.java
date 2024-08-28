import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage represents the storage manager for Reminderebot.
 */
public class Storage {

    /**
     * Reads File and returns contents as an arraylist of Strings.
     * @param filePath
     * @return an arraylist of Tasks
     * @throws FileNotFoundException
     */
    public static ArrayList<String> readFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        ArrayList<String> contents = new ArrayList<>();
        while (s.hasNextLine()) {
            contents.add(s.nextLine());
        }
        return contents;
    }

    /**
     * Write to File, also Handles I/O exception
     * @param textToAdd
     */
    public static void writeToFile(String filePath, String textToAdd) {
        try {
            System.out.println("Added"+ textToAdd + "to" + filePath);
            File file = new File(filePath);

            // This storage location is relative: If run from .bat script,
            // the txt file will be stored at ip/text-ui-test/data/
            // On the other hand, if Reminderebot.java is run directly,
            // the txt file will be stored at ip/data
            File dir = new File(file.getParent());

            boolean dirCreated = dir.mkdirs();
            System.out.println(dirCreated);
            if (file.createNewFile()) {
                FileWriter fw = new FileWriter(filePath);
                fw.write(textToAdd);
                fw.close();
            } else {
                FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
                fw.write("\n" + textToAdd);
                fw.close();
            }
        } catch (IOException e) {
            throw new RuntimeException("This should never happen", e);
        }
    }

    /**
     * Prints file contents.
     * @param filePath
     * @throws FileNotFoundException
     */
    public static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }
}
