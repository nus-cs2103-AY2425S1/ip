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
     * @throws FileNotFoundException If the file is not present.
     * @throws IllegalInputPotongException If the task input is wrong.
     */
    public Storage(TaskList tasklist) throws FileNotFoundException, IllegalInputPotongException {
        this.tasklist = tasklist;
        this.loadFile();
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
     * @throws FileNotFoundException If the file is not present.
     * @throws IllegalInputPotongException If the task input is wrong.
     */
    public void loadFile() throws FileNotFoundException, IllegalInputPotongException {
        ArrayList<Task> result = new ArrayList<>(100);
        File f = new File(this.FILE_PATH);
        Scanner s = new Scanner(f);
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
     * @throws IOException If the input/output is wrong.
     */
    public void writeToFile(String textToAdd) throws IOException {
        this.createFile();
        FileWriter fw = new FileWriter(this.FILE_PATH);
        fw.write(textToAdd);
        fw.close();
    }
}
