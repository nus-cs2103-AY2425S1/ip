package mgtow.storage;

import mgtow.task.Task;
import mgtow.util.InvalidTaskException;
import mgtow.util.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages the storage operations for the MGTOW application.
 * This class handles saving and loading tasks to/from a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a new Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks will be stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        createDirectoryIfNotExists();
    }

    /**
     * Creates the directory for the storage file if it doesn't exist.
     * This method is called during the initialization of the Storage object.
     */
    private void createDirectoryIfNotExists() {
        File directory = new File(filePath).getParentFile();
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (created) {
                System.out.println("\tCreated directory: " + directory.getAbsolutePath());
            } else {
                System.out.println("\tFailed to create directory: " + directory.getAbsolutePath());
            }
        }
    }


    /**
     * Loads tasks from the storage file.
     *
     * @return An ArrayList of Task objects loaded from the file.
     * @throws InvalidTaskException If there's an error parsing the tasks from the file.
     */
    public ArrayList<Task> loadTasks() throws InvalidTaskException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                tasks.add(Parser.parseTaskFromStorage(line));
            }
        } catch (FileNotFoundException e) {
            throw new InvalidTaskException("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the given list of tasks to the storage file.
     *
     * @param tasks The ArrayList of Task objects to be saved.
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.println(Parser.taskToStorageString(task));
            }
        } catch (IOException e) {
            System.out.println("\tError saving tasks: " + e.getMessage());
        }
    }
}