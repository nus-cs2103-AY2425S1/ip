package krona.storage;

import krona.task.Task;
import krona.task.TaskList;
import krona.exception.KronaException;
import krona.parser.Parser;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles loading and saving tasks to a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks will be stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified by the file path.
     *
     * @return A list of tasks loaded from the file.
     * @throws KronaException If an error occurs while reading the file.
     */
    public ArrayList<Task> load() throws KronaException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    Task task = Parser.parseTask(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                }
                scanner.close();
            }
        } catch (IOException e) {
            throw new KronaException("Error reading file");
        }
        return tasks;
    }

    /**
     * Saves the given list of tasks to the file specified by the file path.
     *
     * @param tasks The TaskList containing the tasks to be saved.
     * @throws KronaException If an error occurs while writing to the file.
     */
    public void save(TaskList tasks) throws KronaException {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks.getTasks()) {
                writer.write(Parser.taskToString(task) + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            throw new KronaException("Error saving to file.");
        }
    }
}
