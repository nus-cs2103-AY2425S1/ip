package alfred.storage;

import alfred.ui.Ui;
import alfred.task.Task;
import alfred.exception.AlfredException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the loading and saving of tasks to and from a file.
 * This class is responsible for interacting with the file system
 * to store the current state of tasks and retrieve them upon startup.
 */
public class Storage {
    private Path filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks will be stored.
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    /**
     * Loads the list of tasks from the storage file.
     *
     * @return A list of tasks loaded from the file. If the file does not exist, an empty list is returned.
     * @throws IOException If there is an error reading the file.
     * @throws AlfredException If there is an error converting the file contents into tasks.
     */
    public List<Task> loadTasks() throws IOException, AlfredException {
        List<Task> taskList = new ArrayList<>();

        if (!Files.exists(filePath)) {
            return taskList; // Start with an empty list if file does not exist
        }

        BufferedReader reader = Files.newBufferedReader(filePath);
        String line;
        while ((line = reader.readLine()) != null) {
            Task task = Task.fromFileFormat(line);
            taskList.add(task);
        }
        reader.close();
        return taskList;
    }

    /**
     * Saves the current list of tasks to the storage file.
     *
     * @param taskList The list of tasks to be saved to the file.
     */
    public void saveTasks(List<Task> taskList) {
        try {
            Files.createDirectories(filePath.getParent()); // Ensure the folder exists
            BufferedWriter writer = Files.newBufferedWriter(filePath);
            for (Task task : taskList) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            Ui.showSavingError(e);
        }
    }

    /**
     * Clears the storage file by deleting it from the file system.
     * This will remove all saved tasks.
     */
    public void clearStorage() {
        try {
            Files.delete(filePath);
        } catch (IOException e) {
            Ui.showDeletionError(e);
        }
    }
}
