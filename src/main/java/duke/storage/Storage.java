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
    private static final String DEFAULT_FILE = "duke";
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
        String fileName = getFullFileName();
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
        String fileName = getFullFileName();
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
        saveData();
        currentFile = fileName;
        readData();
        System.out.println("Switched to file: " + getFullFileName());
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
     * Gets the full file name including the appropriate extension.
     *
     * @return The full file name
     */
    private static String getFullFileName() {
        return currentFile + (isTestMode ? "_test.txt" : ".txt");
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
        File testFile = new File(DATA_DIR + getFullFileName());
        if (testFile.exists()) {
            boolean deleted = testFile.delete();
            if (!deleted) {
                System.err.println("Failed to delete test file: " + testFile.getAbsolutePath());
            }
        }
    }

    /**
     * Archives the current task list to a new file.
     * The archive file name is created by appending "_archive" to the current file name.
     *
     * @return The name of the archive file created
     * @throws IOException If there's an error writing to the archive file
     */
    public String archiveTasks() throws IOException {
        String currentFileName = getFullFileName();
        String archiveFileName = currentFileName.replace(".txt", "_archive.txt");

        File currentFile = new File(DATA_DIR + currentFileName);
        File archiveFile = new File(DATA_DIR + archiveFileName);

        if (currentFile.exists()) {
            java.nio.file.Files.copy(
                    currentFile.toPath(),
                    archiveFile.toPath(),
                    java.nio.file.StandardCopyOption.REPLACE_EXISTING
            );

            // Clear the current file
            new FileWriter(DATA_DIR + currentFileName, false).close();

            // Clear the TaskList
            TaskList.getInstance().clearTasks();

            return archiveFileName;
        } else {
            throw new IOException("Current file does not exist.");
        }
    }
}
