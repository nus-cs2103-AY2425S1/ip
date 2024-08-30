package nugget;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The {@code Storage} class is responsible for handling the storage of tasks in a file.
 * It provides functionality to load tasks from a file and save tasks to a file.
 */
public class Storage {
    private Path filePath;

    /**
     * Constructs a {@code Storage} object with the specified file path.
     * Creates the file and its parent directory if they do not exist.
     *
     * @param filePath The path to the file where tasks will be stored.
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
        createFileIfNotExists();
    }

    /**
     * Loads tasks from the file specified by the file path.
     * Each line in the file represents a task, which is converted back to a {@code Task} object.
     *
     * @return An {@code ArrayList} of tasks loaded from the file.
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        try (Scanner scanner = new Scanner(filePath.toFile())) {
            while (scanner.hasNext()) {
                String taskString = scanner.nextLine();
                // Convert taskString back to a nugget.Task object and add to tasks
                // Assume a parseTask method that handles this conversion
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the specified list of tasks to the file.
     * Each task is written as a new line in the file.
     *
     * @param tasks An {@code ArrayList} of tasks to be saved to the file.
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try (FileWriter writer = new FileWriter(filePath.toString())) {
            for (Task task : tasks) {
                writer.write(task.toString() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Creates the file and its parent directory if they do not exist.
     * This method is called during the construction of the {@code Storage} object.
     */
    private void createFileIfNotExists() {
        try {
            if (Files.notExists(filePath.getParent())) {
                Files.createDirectory(filePath.getParent());
            }
            if (Files.notExists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }
}
