package peppa.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import peppa.data.PeppaException;
import peppa.data.TaskList;


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
     * @throws PeppaException if the file cannot be loaded.
     */
    public TaskList load() throws PeppaException {
        try {
            return FileReading.loadTasks(filePath);
        } catch (FileNotFoundException e) {
            try {
                FileReading.createDirectory(getDirectoryName());
                FileReading.createFile(filePath);
                throw new PeppaException("I see this is your first time using Peppa, let me set it up for you!");
            } catch (IOException ex) {
                throw new PeppaException("Error: Failed to create file or directory at: " + filePath);
            }
        }
    }

    private String getDirectoryName() {
        return new File(filePath).getParent(); // Extracts the directory path
    }
}
