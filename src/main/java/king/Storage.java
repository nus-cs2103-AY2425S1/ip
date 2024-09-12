package king;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import king.task.Task;

/**
 * The Storage class is responsible for reading and writing tasks to and from a file.
 * It handles the storage of tasks by saving them to a file and loading them back when needed.
 */
public class Storage {
    private String filePath;

    private TaskList taskList = new TaskList(); // Store task list

    /**
     * Constructs a Storage object with the specified file path.
     * Ensures that the directory for the file exists.
     *
     * @param filePath the path to the file where tasks will be stored
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        ensureDirectoryExists();
    }

    /**
     * Ensures that the directory for the specified file path exists.
     * If the directory does not exist, it creates it.
     */
    private void ensureDirectoryExists() {
        File directory = new File(new File(filePath).getParent());
    }

    /**
     * Saves the list of tasks to the file specified by the file path.
     *
     * @param tasks the list of tasks to be saved
     * @throws KingException if an error occurs while saving the tasks
     */
    public void save(ArrayList<Task> tasks) throws KingException {
        try (FileWriter writer = new FileWriter(this.filePath)) {
            for (Task task : tasks) {
                writer.write(task.toFileString());
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new KingException("An error occurred while saving tasks.");
        }
    }

    /**
     * Loads the list of tasks from the file specified by the file path.
     *
     * @return the list of tasks loaded from the file
     * @throws KingException if an error occurs while loading the tasks
     */
    public ArrayList<Task> loadTasks() throws KingException {
        File file = new File(this.filePath);
        ArrayList<Task> tasks = new ArrayList<>();
        if (!file.exists()) {
            return tasks; // File does not exist; return empty list
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = Task.fromFileString(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            throw new KingException("An error occurred while loading tasks.");
        }
        return tasks;
    }
}
