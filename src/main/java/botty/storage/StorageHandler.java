package botty.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

import botty.exceptions.BottyException;
import botty.tasks.Task;
import botty.tasks.TaskManager;

/**
 * Handles saving and loading from local storage
 */
public class StorageHandler {
    // The path to the directory to store the file
    private final String directoryPath;
    // The file name
    private final String fileName;

    /**
     * Constructs a {@code StorageHandler}. If the directory does not exist, it will create it.
     * @param directoryPath the path to the directory.
     * @param fileName the file name.
     */
    public StorageHandler(String directoryPath, String fileName) {
        this.directoryPath = directoryPath;
        this.fileName = fileName;

        ensureDirectoryExists();
    }

    /**
     * Checks if the directory exists and if it does not, creates it.
     */
    private void ensureDirectoryExists() {
        Path path = Paths.get(directoryPath);
        try {
            Files.createDirectories(path);
        } catch (IOException ex) {
            System.err.println("Failed to create directory: " + directoryPath);
            ex.printStackTrace();
        }
    }

    /**
     * Saves the task list to the file.
     * @param manager the task manager consisting the tasks to be stored.
     * @throws BottyException if an error occurs while reading the tasks from task manager.
     */
    public void saveTaskList(TaskManager manager) throws BottyException {
        Path filePath = Paths.get(directoryPath, fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (int i = 0; i < manager.size(); i++) {
                writer.write(manager.getTask(i).toDataString());
                writer.newLine();
            }
        } catch (IOException ex) {
            System.err.println("IO Exception occurred while writing file");
            ex.printStackTrace();
        }
    }
    /**
     * Loads the task list to the file.
     * @param manager the task manager consisting the tasks to be loaded.
     * @throws BottyException if an error occurs while loading the tasks from task manager.
     */
    public void loadTaskList(TaskManager manager) throws BottyException {
        Path filePath = Paths.get(directoryPath, fileName);
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;

            while ((line = reader.readLine()) != null) {
                manager.addTask(Task.fromDataString(line));
            }
        } catch (NoSuchFileException ex) {
            // if file not found, create the file
            try {
                Files.createFile(filePath);
            } catch (IOException ioEx) {
                System.err.println("IO Exception occurred while creating file");
                ex.printStackTrace();
            }
        } catch (IOException ex) {
            System.err.println("IO Exception occurred while reading file");
            ex.printStackTrace();
        }
    }

}
