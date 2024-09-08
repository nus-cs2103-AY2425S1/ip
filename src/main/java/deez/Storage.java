package deez;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Storage class for reading and writing Deez objects to a file.
 */
public class Storage {
    /**
     * Final constant representing the name of the storage file.
     */
    static final String FILE_NAME = "deez.txt";

    private String filePath;

    /**
     * Initializes a Storage object with a specified file path.
     *
     * @param filePath The path to use for storage.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Checks if the file exists at the given path.
     *
     * @return True if the file exists, false otherwise
     */
    private boolean fileExists() {
        assert filePath != null && !filePath.isBlank();
        return new File(filePath + File.separator + FILE_NAME).exists();
    }

    /**
     * Checks if the file is loaded and ready to be read.
     *
     * @return True if the file exists, false otherwise
     */
    public boolean canLoad() {
        return fileExists();
    }

    /**
     * Loads tasks from a stored file into an ArrayList.
     *
     * @throws IOException            If there is an issue with reading the file.
     * @throws ClassNotFoundException If the stored class is not recognized.
     */
    public TaskList loadTasks() throws IOException, ClassNotFoundException {
        assert filePath != null && !filePath.isBlank();
        FileInputStream fileInputStream = new FileInputStream(filePath + File.separator + FILE_NAME);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        return (TaskList) objectInputStream.readObject();
    }

    /**
     * Saves the given task list to a file.
     *
     * @param taskList The tasks to be saved
     */
    public void saveTasks(TaskList taskList) {
        assert taskList != null; // Must not save a null taskList
        if (!fileExists()) {
            File dir = new File(filePath);
            dir.mkdirs();
        }
        try {
            FileOutputStream fileOutputStream =
                new FileOutputStream(filePath + File.separator + FILE_NAME);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(taskList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DeezException("Failed to save.");
        }
    }
}
