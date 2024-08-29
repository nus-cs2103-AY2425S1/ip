package bob.storage;

import bob.data.BobException;
import bob.data.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Handles the storage of tasks.
 */
public class Storage {
    protected static String filePath;

    /**
     * Creates a new Storage instance.
     *
     * @param filePath The path to the file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Creates a new Storage instance if the file does not exist.
     *
     * @throws IOException If the file cannot be created.
     */
    public Storage() throws IOException {
        FileReading.createDirectory(getDirectoryName());
        FileReading.createFile(filePath);
    }

    /**
     * Loads the tasks from the file.
     *
     * @return TaskList The list of tasks.
     * @throws BobException If the file cannot be loaded.
     */
    public TaskList load() throws BobException {
        try {
            return FileReading.loadTasks(filePath);
        } catch (FileNotFoundException e) {
            try {
                FileReading.createDirectory(getDirectoryName());
                FileReading.createFile(filePath);
                throw new BobException("I see this is your first time using Bob, let me set it up for you!");
            } catch (IOException ex) {
                throw new BobException("Error: Failed to create file or directory at: " + filePath);
            }
        }
    }

    /**
     * Returns the directory path of the file.
     *
     * @return The directory path.
     */
    private String getDirectoryName() {
        return new File(filePath).getParent(); // Extracts the directory path
    }
}
