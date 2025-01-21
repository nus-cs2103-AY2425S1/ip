package morgana.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import morgana.exceptions.MorganaException;
import morgana.parser.Parser;
import morgana.task.Task;
import morgana.task.TaskList;

/**
 * Handles the persistent storage of tasks, providing methods to read tasks
 * from a file and write tasks to a file.
 */
public class Storage {
    private final File file;

    /**
     * Constructs a {@code Storage} object with the specified file path.
     * If the file does not exist, it will be created along with any necessary directories.
     *
     * @param filePath The path to the file where tasks will be stored.
     * @throws MorganaException If an error occurs while initializing the storage.
     */
    public Storage(String filePath) throws MorganaException {
        file = new File(filePath);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new MorganaException("Failed to initialize storage: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the associated storage file.
     *
     * @return A list of tasks loaded from the file.
     * @throws MorganaException If an error occurs while loading the tasks.
     */
    public List<Task> load() throws MorganaException {
        List<Task> tasks = new ArrayList<>();
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                tasks.add(Parser.parseTask(sc.nextLine()));
            }
        } catch (FileNotFoundException e) {
            throw new MorganaException("Failed to load tasks from file: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the given list of tasks to the associated storage file.
     * Each task is converted to a file-friendly format before being written to the file.
     *
     * @param tasks The {@code TaskList} containing the tasks to be saved.
     * @throws MorganaException If an error occurs while saving the tasks.
     */
    public void save(TaskList tasks) throws MorganaException {
        try (FileWriter writer = new FileWriter(file)) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat() + "\n");
            }
        } catch (IOException e) {
            throw new MorganaException("Failed to save tasks to file: " + e.getMessage());
        }
    }
}
