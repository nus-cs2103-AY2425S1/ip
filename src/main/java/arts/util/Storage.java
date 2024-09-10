package arts.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import arts.ArtsException;
import arts.task.Task;

/**
 * The Storage class handles the loading and saving of tasks to and from a file.
 * It manages the persistence of task data, allowing tasks to be stored and retrieved.
 */
public class Storage {
    private final String filePath;
    private static final String NO_TASK_FILE_MESSAGE = "No existing task file found. Starting fresh.";

    /**
     * Constructs a Storage object with the specified file path for storing tasks.
     *
     * @param filePath The path of the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified by the filePath.
     * If the file does not exist, it initializes an empty task list.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws ArtsException If there is an error reading the file or parsing tasks.
     */
    public ArrayList<Task> load() throws ArtsException {
        File file = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<>();

        if (!file.exists()) {
            System.out.println(NO_TASK_FILE_MESSAGE);
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Task.fromFileFormat(line);
                tasks.add(task);
            }
        } catch (IOException | ArtsException e) {
            throw new ArtsException("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the provided list of tasks to the file specified by the filePath.
     *
     * @param tasks The list of tasks to be saved to the file.
     * @throws ArtsException If there is an error writing to the file.
     */
    public void save(ArrayList<Task> tasks) throws ArtsException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new ArtsException("Error saving tasks: " + e.getMessage());
        }
    }
}
