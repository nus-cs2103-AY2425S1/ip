package bob.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import bob.data.BobException;
import bob.data.TaskList;


/**
 * Represents the storage of tasks.
 */
public class Storage {
    protected static String filePath;

    /**
     * Constructor for the Storage class if the file already exists.
     *
     * @param filePath the path to the file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Constructor for the Storage class if the file does not exist.
     *
     * @throws IOException if the file cannot be created.
     */
    public Storage() throws IOException {
        FileReading.createDirectory(getDirectoryName());
        FileReading.createFile(filePath);
    }

    /**
     * Loads the tasks from the file.
     *
     * @return the list of tasks.
     * @throws BobException if the file cannot be loaded.
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

    private String getDirectoryName() {
        return new File(filePath).getParent(); // Extracts the directory path
    }
}
