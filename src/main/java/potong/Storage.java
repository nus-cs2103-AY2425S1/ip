package potong;

import potong.exceptions.IllegalInputPotongException;

import potong.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class to save and load data.
 */
public class Storage {
    private final String DIR_PATH = "./src/main/data";
    private final String FILE_PATH = DIR_PATH + "/potong.txt";

    private TaskList tasklist;

    /**
     * Initialise the class, create the file to save if not already present.
     * @param tasklist List of tasks to load the saved tasks.
     */
    public Storage(TaskList tasklist) {
        this.tasklist = tasklist;
        try {
            this.loadFile();
        } catch (IllegalInputPotongException e) {
            throw new RuntimeException(e);
        }
        this.createFile();
    }

    /**
     * Create the directory where the saved file is.
     */
    public void createDirectory() {
        File d = new File(this.DIR_PATH);
        d.mkdirs();
    }

    /**
     * Create the file if not present.
     */
    public void createFile() {
        this.createDirectory();
        File f = new File(this.FILE_PATH);
    }

    /**
     * Load the saved data from the saved file.
     *
     * @throws IllegalInputPotongException If the task input is wrong.
     */
    public void loadFile() throws IllegalInputPotongException {
        ArrayList<Task> result = new ArrayList<>(100);
        File f = new File(this.FILE_PATH);
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String curr = s.nextLine();
                if (curr.isEmpty()) {
                    break;
                }
                Task nextTask = Parser.createTask(curr);
                result.add(nextTask);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
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
            FileWriter fw = new FileWriter(this.FILE_PATH);
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
