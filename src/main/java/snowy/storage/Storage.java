package snowy.storage;

import snowy.tasklist.Task;
import snowy.tasklist.TaskList;
import snowy.data.SnowyException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles saving and loading of tasks from a file.
 */
public class Storage {

    private final Path taskFilePath;

    public Storage(String directoryPath, String fileName) {
        Path dataDirectoryPath = Paths.get(directoryPath);
        this.taskFilePath = dataDirectoryPath.resolve(fileName);

        try {
            if (Files.notExists(dataDirectoryPath)) {
                Files.createDirectories(dataDirectoryPath);
            }
            if (Files.notExists(taskFilePath)) {
                Files.createFile(taskFilePath);
            } else {
                clearFile();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while setting up storage: " + e.getMessage());
        }
    }

    private void clearFile() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(taskFilePath.toString(), false))) {
            writer.write(""); // Write an empty string to clear the file
        }
    }
    public void writeTaskToFile(Task task) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(taskFilePath.toString(), true))) {
            writer.write(task.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    public void deleteTaskFromFile(Task task) {
        Path tempFilePath = taskFilePath.getParent().resolve("temp.txt");
        String search = task.toString();

        try (
                BufferedReader br = new BufferedReader(new FileReader(taskFilePath.toString()));
                BufferedWriter bw = new BufferedWriter(new FileWriter(tempFilePath.toString()))
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.equals(search)) {
                    bw.write(line);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while deleting the task from the file: " + e.getMessage());
            return;
        }

        // Replace the original file with the temporary file
        try {
            Files.delete(taskFilePath);
            Files.move(tempFilePath, taskFilePath);
            System.out.println("Task deleted from file successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while replacing the file: " + e.getMessage());
        }
    }

    public List<String> loadTasksFromFile() {
        List<String> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(taskFilePath.toString()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading from the file: " + e.getMessage());
        }
        return tasks;
    }
}
