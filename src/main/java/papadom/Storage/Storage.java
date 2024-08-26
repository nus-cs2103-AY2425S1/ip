package papadom.Storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import papadom.tasks.Task;
/**
 * Manages the storage of tasks in a file.
 */
public class Storage {
    private final String filePath;
    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path of the file used for storage.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }
    /**
     * Adds a task to the storage database by writing it to the file.
     *
     * @param task The task to be added to the file.
     */
    public void addTaskToDatabase(Task task) {
        try {
            FileWriter fw = new FileWriter(this.filePath, true);
            fw.write(task.toString() + "\n");
            fw.close();
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
    /**
     * Creates the storage file if it does not exist, including any necessary directories.
     */
    public void createFileIfNotPresent() {
        File file = new File(filePath);
        try {
            // Check if the file exists
            if (!file.exists()) {
                // If the file doesn't exist, create it along with any necessary directories
                file.getParentFile().mkdirs(); // Create directories if they don't exist
                file.createNewFile();
            }
        } catch (IOException e) {
            System.err.println("An error occurred while creating the file: " + e.getMessage());
        }
    }
}
