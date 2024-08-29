package echobot;
import echobot.task.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

/**
 * Handles the storage of tasks by reading from and writing to a file.
 * Ensures that the file and directory exist and provides methods for saving and loading tasks.
 */
public class Storage {
    private static final String FILE_PATH = "./data/echo-bot.txt";
    private static final String DIR_PATH = "./data/";

    /**
     * Ensures that the directory and file for storing tasks exist.
     * Creates the directory and file if they do not already exist.
     */
    public static void ensureFileExists() {
        File dir = new File(DIR_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
            return; // Create the directory if it doesn't exist
        }

        File file = new File(FILE_PATH);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    /**
     * Saves the tasks to the file.
     * Ensures the file exists before writing and formats each task for storage.
     *
     * @param tasks The list of tasks to be saved.
     */
    public static void saveTasksToFile(TaskList tasks) {
        ensureFileExists();
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks.getTasks()) {
                writer.println(task.toFileFormat());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the file into the given TaskList.
     * Handles any errors that occur during file reading and task parsing.
     *
     * @param tasks The list to which the loaded tasks will be added.
     */
    public static void loadTasksFromFile(TaskList tasks) {
        ensureFileExists();
        Path path = Paths.get(FILE_PATH);
        if (!Files.exists(path)) {
            // No file to load from
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Task task = getTask(line);
                    tasks.addTask(task);
                } catch (Exception e) {
                    System.out.println("Corrupted line in file: " + line);
                    System.out.println(e.getMessage());
                    // Optionally, delete or reset the file if corruption detected:
                    // new File(FILE_PATH).delete(); // Resets the file
                    // System.out.println("File reset due to corruption.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }

    /**
     * Creates a Task object from a file line.
     * Parses the line to determine the task type and properties.
     *
     * @param line The line from the file to be parsed.
     * @return The Task object created from the line.
     * @throws IllegalArgumentException If the task type is unknown or the line format is incorrect.
     */
    private static Task getTask(String line) {
        String[] parts = line.split(" \\| ");
        Task task = null;
        switch (parts[0]) {
            case "T":
                task = new Todo(parts[2]);
                break;
            case "D":
                task = new Deadline(parts[2], parts[3]);
                break;
            case "E":
                task = new Event(parts[2], parts[3], parts[4]);
                break;
            default:
                throw new IllegalArgumentException("Unknown task type");
        }
        if (parts[1].equals("1")) {
            task.markAsDone();
        }
        return task;
    }
}
