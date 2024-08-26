package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * The Storage class handles reading from and writing to the file system.
 * It follows the Singleton pattern to ensure only one instance exists.
 */
public class Storage {

    private static Storage storage;
    private static final String DATA_DIR = "data/";
    private static final String MAIN_FILE = "duke.txt";
    private static final String TEST_FILE = "duke_test.txt";
    private static boolean isTestMode = false;

    /**
     * Gets the singleton instance of the Storage class.
     *
     * @return The Storage instance
     */
    public static Storage getInstance() {
        if (storage == null) {
            storage = new Storage();
        }
        return storage;
    }

    /**
     * Reads data from the file system and loads it into the TaskList.
     * Creates necessary directories and files if they don't exist.
     */
    public void readData() {
        String fileName = isTestMode ? TEST_FILE : MAIN_FILE;
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            File filedir = new File(DATA_DIR);
            if (!filedir.exists()) {
                boolean success = filedir.mkdir();
            }
            File file = new File(DATA_DIR + fileName);
            if (!file.exists()) {
                boolean success = file.createNewFile();
            }

            Scanner scan = new Scanner(file);
            TaskList taskList = TaskList.getInstance();
            while (scan.hasNext()) {
                taskList.loadData(scan.nextLine());
            }
            scan.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Writes the current TaskList data to the file system.
     */
    public void writeData() {
        String fileName = isTestMode ? TEST_FILE : MAIN_FILE;
        try {
            FileWriter filewriter = new FileWriter(DATA_DIR + fileName);
            ArrayList<Task> storage = TaskList.getInstance().getTaskList();
            for (Task t : storage) {
                filewriter.write(t.saveFormat() + "\n");
            }
            filewriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Static method to save data to the file system using the singleton instance.
     */
    public static void saveData() {
        Storage file = Storage.getInstance();
        file.writeData();
    }

    /**
     * Sets up and enters testing environment.
     */
    public static void startTest() {
        isTestMode = true;
        TaskList.getInstance().clearTasks();
        getInstance().readData();
    }

    /**
     * Shuts down and exits testing environment.
     */
    public static void endTest() {
        deleteTestFile();
        isTestMode = false;
        TaskList.getInstance().clearTasks();
    }

    /**
     * Function to delete data file generated during tests.
     */
    private static void deleteTestFile() {
        File testFile = new File(DATA_DIR + TEST_FILE);
        if (testFile.exists()) {
            boolean deleted = testFile.delete();
            if (!deleted) {
                System.err.println("Failed to delete test file: " + testFile.getAbsolutePath());
            }
        }
    }
}
