package potong;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import potong.task.Task;

/**
 * Class to save and load data.
 */
public class Storage {
    private final String DIRECTORY_NAME = "./data";
    private final String FILE_NAME = "/potong.txt";

    private final Path FILE_PATH = Paths.get(this.DIRECTORY_NAME, this.FILE_NAME);


    private TaskList tasklist;

    /**
     * Initialises the class, create the file to save if not already present.
     * @param tasklist List of tasks to load the saved tasks.
     */
    public Storage(TaskList tasklist) {
        this.tasklist = tasklist;
        this.createFile();
        this.loadFile();
    }

    /**
     * Creates the directory where the saved file is.
     */
    public void createDirectory() {
        try {
            Files.createDirectories(Paths.get(this.DIRECTORY_NAME));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Creates the file if not present.
     */
    public void createFile() {
        this.createDirectory();
        try {
            if (Files.notExists(this.FILE_PATH)) {
                Files.createFile(this.FILE_PATH);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Load the saved data from the saved file.
     */
    public void loadFile() {
        ArrayList<Task> result = new ArrayList<>(100);
        File f = this.FILE_PATH.toFile();
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        while (true) {
            assert s != null;
            if (!s.hasNext()) {
                break;
            }
            String curr = s.nextLine();
            if (curr.isEmpty()) {
                break;
            }
            Task nextTask = Parser.loadSavedTasks(curr);
            result.add(nextTask);
        }
        this.tasklist.initialise(result);
    }

    /**
     * Save the list into the file.
     *
     * @param textToAdd String representation of the list.
     */
    public void writeToFile(String textToAdd) {
        this.createFile();
        try {
            FileWriter fw = new FileWriter(this.FILE_PATH.toFile());
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
