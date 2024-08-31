package storage;

import exceptions.BottyException;
import tasks.Task;
import tasks.TaskManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StorageHandler {
    private final String directoryPath;
    private final String fileName;
    public StorageHandler(String directoryPath, String fileName) {
        this.directoryPath = directoryPath;
        this.fileName = fileName;

        ensureDirectoryExists();
    }
    private void ensureDirectoryExists() {
        Path path = Paths.get(directoryPath);
        try {
            Files.createDirectories(path);
        } catch (IOException ex) {
            System.err.println("Failed to create directory: " + directoryPath);
            ex.printStackTrace();
        }
    }
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
