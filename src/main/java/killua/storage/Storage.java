package killua.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import killua.parser.Parser;
import killua.task.Task;
import killua.util.KilluaException;
import killua.util.TaskList;

/**
 * Handles the loading and saving of tasks to and from a file.
 * It manages the file operations necessary to persist the task list.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified by the file path.
     * If the file does not exist, an empty TaskList is returned.
     *
     * @return A TaskList containing the tasks read from the file.
     */
    public TaskList load() {
        TaskList tasks = new TaskList();
        File file = new File(filePath);

        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    Task task = Parser.parseTaskFromHistory(line);
                    if (task != null) {
                        tasks.addTask(task);
                    }
                }
            } catch (FileNotFoundException | KilluaException e) {
                throw new RuntimeException(e);
            }
        }

        return tasks;
    }

    /**
     * Saves the current tasks to the file specified by the file path.
     * The file is created if it does not exist, and the directory is created if necessary.
     *
     * @param tasks The TaskList containing the tasks to be saved.
     * @throws IOException If an error occurs while writing to the file.
     */
    public void save(TaskList tasks) throws IOException {
        File directory = new File("./data");
        // if file does not exist, create the file
        if (!directory.exists()) {
            directory.mkdirs();
        }
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasks.getTasks()) {
            fw.write(task.toSaveAsString() + System.lineSeparator());
        }
        fw.close();
    }
}
