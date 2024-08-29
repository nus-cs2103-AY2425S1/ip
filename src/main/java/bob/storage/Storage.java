package bob.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import bob.data.BobException;
import bob.data.TaskList;

public class Storage {
    protected static String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public Storage() throws IOException {
        FileReading.createDirectory(getDirectoryName());
        FileReading.createFile(filePath);
    }

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
