import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.nio.file.Files;
/**
 * This class implements a storage, which saves and loads the tasklist from
 * and to file. This class stores data as a comma-separated-variable file.
 * It supports the following methods:
 * (i)
 */
public class Storage {

    private Storage() {
    }

    /** The default file path for storing and writing data */
    private static final Path FILE_PATH = Path.of("./data/tasks.csv");
    /** The default directory path for storing and writing data */
    private static final Path DIRECTORY_PATH = Path.of("./data/");

    /**
     * Saves the current TaskList to file. The tasks are converted into csv
     * representations as per their .toCsv() methods, and written to a file
     * indicated by FILE_PATH.
     */
    static void save() {
        //Checks if the directory already exists. If not, creates the directory.
        if (Files.notExists(DIRECTORY_PATH)) {
            try {
                Files.createDirectories(DIRECTORY_PATH);
            } catch (IOException e) {
                System.out.println("""
                        Oh no! I can't create the file directory.
                        Check out the error message to see what went wrong
                        """);
                e.printStackTrace();
            }
        }
        //Checks if the save file already exists. If not, creates the file.
        if (Files.notExists(FILE_PATH)) {
            try {
                Files.createFile(FILE_PATH);
            } catch (IOException e) {
                System.out.println("""
                        Oh no! I can't create your save file.
                        Check out the error message to see what went wrong
                        """);
                e.printStackTrace();
            }
        }

        //Writes all tasks to file, overwriting the old file
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(FILE_PATH)) {
            ArrayList<String> saveFile = TaskList.toSaveFile();
            for (String lineToWrite: saveFile) {
                bufferedWriter.write(lineToWrite);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Oh no! I can't save your file. \n " +
                    "Check out the error message to see what went wrong");
            e.printStackTrace();
        }
    }

    /**
     * Locates the save file, and then prepares the contents for loading
     * @return An array of strings representing tasks, in csv format.
     */
    static ArrayList<String> load() {
        try (BufferedReader bufferedReader = Files.newBufferedReader(FILE_PATH)) {
            ArrayList<String> tasksToRead = new ArrayList<>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                tasksToRead.add(line);
            }
            //It is safe to cast to String[] since only strings are added to tasksToRead
            return tasksToRead;
        } catch (NoSuchFileException e) {

            //Checks if the directory exists. If not, creates the directory.
            if (Files.notExists(DIRECTORY_PATH)) {
                try {
                    Files.createDirectories(DIRECTORY_PATH);
                } catch (IOException e2) {
                    System.out.println("""
                            Oh no! I can't save your file.
                            Check out the error message to see what went wrong
                            """);
                    e2.printStackTrace();
                }
            }

            //Checks if the save file already exists. If not, creates the file.
            if (Files.notExists(FILE_PATH)) {
                try {
                    Files.createFile(FILE_PATH);
                } catch (IOException e2) {
                    System.out.println("""
                            Oh no! I can't save your file.
                            Check out the error message to see what went wrong
                            """);
                    e2.printStackTrace();
                }
            }
        } catch (IndexOutOfBoundsException e) { //File is likely corrupted.
            try {
                Files.delete(FILE_PATH);
                Files.createFile(FILE_PATH);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("Oh no, I can't read the saved file. \n" +
                    "Check the error message to see why");
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
