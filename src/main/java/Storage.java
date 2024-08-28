import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

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

    public String getFilePath() {
        return filePath;
    }

    private String getDirectoryName() {
        return new File(filePath).getParent(); // Extracts the directory path
    }
}
