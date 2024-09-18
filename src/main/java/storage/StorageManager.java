package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import skibidi.SkibidiException;

/**
 * Manages the storage of tasks in a file.
 */
public class StorageManager {
    private final String filePath;

    /**
     * Creates a new StorageManager.
     *
     * @param filePath The file path to store the tasks.
     */
    public StorageManager(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the tasks to the file.
     *
     * @param tasks The list of tasks to save.
     * @throws IOException If an I/O error occurs.
     */
    public void saveTasks(List<Task> tasks) throws IOException {
        File file = new File(filePath);
        file.getParentFile().mkdirs(); // Create the directory if it doesn't exist

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        }
    }

    /**
     * Loads the tasks from the file.
     *
     * @return The list of tasks loaded from the file.
     * @throws IOException If an I/O error occurs.
     */
    public List<Task> loadTasks() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks; // Return empty list if the file doesn't exist
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTask(line);
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error parsing task data: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Creates the file if it does not exist.
     *
     * @throws IOException If an I/O error occurs.
     */
    public void createFile() throws IOException {
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        file.createNewFile();
    }

    /**
     * Parses a task from a line in the file.
     *
     * @param line The line containing the task data.
     * @return The task parsed from the line.
     */
    private Task parseTask(String line) throws SkibidiException {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2];
        List<Tag> tags = new ArrayList<>();

        if (parts.length > 3) {
            String[] tagParts = parts[parts.length - 1].split(",");
            for (String tagName : tagParts) {
                tags.add(new Tag(tagName.trim()));
            }
        }

        Task task;
        switch (type) {
        case "T":
            task = new Todo(description, isDone);
            break;
        case "D":
            String by = parts[3];
            task = new Deadline(description, by, isDone);
            break;
        case "E":
            String from = parts[3];
            String to = parts[4];
            task = new Event(description, from, to, isDone);
            break;
        default:
            throw new IllegalArgumentException("Invalid task type in file.");
        }

        for (Tag tag : tags) {
            task.addTag(tag);
        }

        return task;
    }
}
