package guy.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import guy.tasks.Task;
import guy.tasks.TaskManager;

/**
 * A storage class, handling reads and writes to the file system.
 * Only one instance exists at a time.
 */
public class Storage {
    private static Storage storage;
    private static final String DATA_DIR = "data/";
    private static final String MAIN_FILE = "guy.txt";
    private static final String TEST_FILE = "guy_test.txt";
    private static boolean isTesting = false;

    /**
     * Retrieves the instance of the Storage class.
     *
     * @return the instance of the class
     */
    public static Storage getInstance() {
        if (storage == null) {
            storage = new Storage();
        }
        return storage;
    }

    /**
     * Reads data from the file system and loads it into the TaskManager.
     * Creates necessary directories and files if not present.
     */
    public void readData() {
        String fileName = isTesting ? TEST_FILE : MAIN_FILE;
        try {
            File dir = new File(DATA_DIR);
            if (!dir.exists()) {
                boolean created = dir.mkdir();
            }

            File f = new File(DATA_DIR + fileName);
            if (!f.exists()) {
                boolean created = f.createNewFile();
            }

            Scanner read = new Scanner(f);
            TaskManager tm = TaskManager.getInstance();
            while (read.hasNext()) {
                tm.loadData(read.nextLine());
            }

            read.close();
        } catch (IOException e) {
            System.out.println("I got a problem, and it sure as f*** ain't my fault! It says: \n" + e.getMessage());
        }
    }

    /**
     * Writes data from the TaskManager to the file system.
     */
    public void writeData() {
        String fileName = isTesting ? TEST_FILE : MAIN_FILE;
        try {
            FileWriter writer = new FileWriter(DATA_DIR + fileName);
            ArrayList<Task> tasks = TaskManager.getInstance().getTasks();
            for (Task task : tasks) {
                writer.write(task.saveFormat() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("I got a problem, and it sure as f*** ain't my fault! It says: \n" + e.getMessage());
        }
    }

    /**
     * Saves data to the Storage instance.
     */
    public static void saveData() {
        Storage f = Storage.getInstance();
        f.writeData();
    }

    /**
     * Loads a testing environment.
     */
    public static void startTest() {
        isTesting = true;
        TaskManager.getInstance().clearTasks();
        getInstance().readData();
    }

    /**
     * Shuts down a testing environment.
     */
    public static void stopTest() {
        isTesting = false;
        TaskManager.getInstance().clearTasks();
        getInstance().readData();
    }

    /**
     * Deletes the test file.
     */
    public static void wipeTest() {
        File testFile = new File(DATA_DIR + TEST_FILE);
        if (testFile.exists()) {
            boolean isDeleted = testFile.delete();
            if (!isDeleted) {
                System.err.println("You really thought I could delete this file: " + testFile.getAbsolutePath());
            }
        }
    }

}
