package potong;

import potong.exceptions.IllegalInputPotongException;

import potong.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class to save and load data.
 */
public class Storage {
    private final String DIRECTORY_NAME = "./data";
    private final String FILE_NAME = "/potong.txt";

    private final Path FILE_PATH = Paths.get(this.DIRECTORY_NAME, this.FILE_NAME);


    private TaskList tasklist;

    /**
     * Initialise the class, create the file to save if not already present.
     * @param tasklist List of tasks to load the saved tasks.
     */
    public Storage(TaskList tasklist) {
        this.tasklist = tasklist;
        this.createFile();
        try {
            this.loadFile();
        } catch (IllegalInputPotongException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Create the directory where the saved file is.
     */
    public void createDirectory() {
        try {
            Files.createDirectory(Paths.get(this.DIRECTORY_NAME));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Create the file if not present.
     */
    public void createFile() {
        this.createDirectory();
        try {
            Files.createFile(this.FILE_PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Load the saved data from the saved file.
     *
     * @throws IllegalInputPotongException If the task input is wrong.
     */
    public void loadFile() throws IllegalInputPotongException {
        ArrayList<Task> result = new ArrayList<>(100);
        File f = this.FILE_PATH.toFile();
        Scanner s;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (s.hasNext()) {
            String curr = s.nextLine();
            if (curr.isEmpty()) {
                break;
            }
            Task nextTask = Parser.createTask(curr);
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
            throw new RuntimeException(e);
        }
    }
}
