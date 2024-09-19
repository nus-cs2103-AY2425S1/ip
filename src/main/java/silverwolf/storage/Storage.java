package silverwolf.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import silverwolf.exception.SilverWolfException;
import silverwolf.task.Task;

/**
 * The Storage class is responsible for handling file operations related to task storage.
 * It ensures that the necessary directory and file are created, and provides methods
 * for reading from and writing to the file. The class supports both file creation
 * and file management tasks, making sure that the application's data is preserved
 * and correctly loaded when needed.
 */
public class Storage {
    private final String directoryPath;
    private final String filePath;
    private final String fullPath;

    /**
     * Constructs a Storage object with the specified file path,
     * automatically extracting the directory path from the file path.
     * This constructor is used when only the file path is known.
     *
     * @param filePath The relative path to the file within the directory.
     */
    public Storage(String filePath) {
        Path path = Paths.get(filePath);
        this.directoryPath = path.getParent() != null ? path.getParent().toString() : "";
        String[] parts = path.getFileName().toString().split("/");
        this.filePath = parts[0];
        this.fullPath = Paths.get(this.directoryPath, this.filePath).toString();
    }

    /**
     * Sets up the storage by ensuring the directory and file exist.
     * Creates them if they do not exist.
     */
    public void setUp() {
        try {
            // create directory if it doesnt exist
            File folder = new File(this.directoryPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            // create file if it doesnt exist
            File saveFile = new File(this.filePath);
            if (!saveFile.exists()) {
                saveFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Hmmm, an error occurred while setting up list storage.");
            e.printStackTrace();
        }
    }

    /**
     * Saves a list of tasks to the file.
     * Each task is written to a new line in the file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws SilverWolfException If an error occurs during file writing.
     */
    public void save(List<Task> tasks) throws SilverWolfException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.fullPath));
            for (Task task : tasks) {
                writer.write(task.toFileString());
                writer.newLine();
            }
            File toBeDeleted = new File(filePath);
            toBeDeleted.delete();
            writer.close();
        } catch (IOException e) {
            throw new SilverWolfException("urm, an error occurred while writing to the file.");
        }

    }

    /**
     * Loads tasks from the file and returns them as a list.
     * Each line in the file is parsed into a Task object.
     *
     * @return A list of tasks loaded from the file.
     * @throws SilverWolfException If an error occurs during file reading or if the file is not found.
     */
    public List<Task> load() throws SilverWolfException {
        List<Task> tasks = new ArrayList<>();
        try {
            File file = new File(this.fullPath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(Task.fromString(line));
            }
        } catch (IOException e) {
            throw new SilverWolfException("Error loading tasks from file");
        }
        return tasks;
    }

}
