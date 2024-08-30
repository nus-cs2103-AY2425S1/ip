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
 * Handles the storage and retrieval of tasks from a file.
 * The Storage class is responsible for reading tasks from a file and
 * saving tasks to a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     * If the file or its parent directories do not exist, they are created.
     *
     * @param filePath The path to the file where tasks will be stored.
     * @throws MorganaException If an error occurs while initializing the storage.
     */
    public Storage(String filePath) throws MorganaException {
        this.filePath = filePath;
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new MorganaException("Failed to initialize storage: %s".formatted(e.getMessage()));
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
        File file = new File(filePath);
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                Task task = Parser.parseTask(sc.nextLine());
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            throw new MorganaException("Failed to load tasks from file: %s".formatted(e.getMessage()));
        }
        return tasks;
    }

    /**
     * Saves the given list of tasks to the associated storage file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws MorganaException If an error occurs while saving the tasks.
     */
    public void save(TaskList tasks) throws MorganaException {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (int i = 0; i < tasks.size(); i++) {
                writer.write("%s%n".formatted(tasks.get(i).toFileFormat()));
            }
        } catch (IOException e) {
            throw new MorganaException("Failed to save tasks to file: %s".formatted(e.getMessage()));
        }
    }
}
