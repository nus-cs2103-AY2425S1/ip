package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
    private static final String DEFAULT_FILE = "duke.txt";
    private static final String TEST_FILE = "duke_test.txt";
    private static boolean isTestMode = false;
    private static String currentFile = DEFAULT_FILE;

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
     * Reads data from the current file and loads it into the TaskList.
     * Creates necessary directories and files if they don't exist.
     */
    public void readData() {
        String fileName = isTestMode ? TEST_FILE : currentFile;
        try {
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
            taskList.clearTasks();
            while (scan.hasNext()) {
                taskList.loadData(scan.nextLine());
            }
            scan.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Writes the current TaskList data to the current file.
     */
    public void writeData() {
        String fileName = isTestMode ? TEST_FILE : currentFile;
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
     * Changes the current save file and loads tasks from the new file.
     *
     * @param fileName The name of the new file to use (without .txt extension)
     */
    public void changeFile(String fileName) {
        if (isTestMode) {
            System.out.println("Cannot change file in test mode.");
            return;
        }
        saveData();
        currentFile = fileName + ".txt";
        readData();
        System.out.println("Switched to file: " + currentFile);
    }

    /**
     * Gets the name of the current file being used.
     *
     * @return The name of the current file
     */
    public String getCurrentFile() {
        return currentFile;
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
        currentFile = DEFAULT_FILE;
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
