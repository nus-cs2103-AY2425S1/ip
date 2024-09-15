package popi.task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.List;

import popi.exception.PopiException;

/**
 * Manages the loading and saving of tasks from and to the file.
 */
public class TaskManager {
    private static final String PATH = "data/popi.txt";

    /**
     * Loads tasks from the file.
     *
     * @return Task list loaded from the file.
     * @throws PopiException If there is an error loading the tasks.
     */
    public TaskList loadTasks() throws PopiException {
        return load();
    }

    protected TaskList load() throws PopiException {
        TaskList tasks = new TaskList();
        File f = new File(PATH);

        // Create file if it does not exist
        if (!f.exists()) {
            try {
                boolean directoryCreated = f.getParentFile().mkdirs();
                assert directoryCreated : "Failed to create directory: " + f.getParentFile();

                boolean fileCreated = f.createNewFile();
                assert fileCreated : "Failed to create file: " + PATH;
            } catch (IOException e) {
                throw new PopiException("Error creating file");
            }
            return tasks;
        }

        // Load tasks from file
        try {
            List<String> lines = Files.readAllLines(java.nio.file.Paths.get(PATH));
            for (String line : lines) {
                Task t = parseTask(line);
                tasks.addTask(t);
            }
        } catch (IOException e) {
            throw new PopiException("Error reading file");
        }
        return tasks;
    }

    private static Task parseTask(String line) throws PopiException {
        String[] parts = line.split(" \\| ");
        Task t = switch (parts[0]) {
        case "T" -> new Todo(parts[2]);
        case "D" -> new Deadline(parts[2], LocalDateTime.parse(parts[3]));
        case "E" -> new Event(parts[2], LocalDateTime.parse(parts[3]), LocalDateTime.parse(parts[4]));
        default -> throw new PopiException("Invalid task type");
        };
        if (parts[1].equals("1")) {
            t.markAsDone();
        }
        return t;
    }

    /**
     * Saves the tasks to the file.
     *
     * @param task The list of tasks to be saved.
     * @throws PopiException If there is an error saving the tasks.
     */
    public void save(TaskList task) throws PopiException {
        try {
            FileWriter writer = new FileWriter(PATH);
            for (Task t : task.getAllTasks()) {
                assert t != null : "Task in the list is null";
                writer.write(t.toDataString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new PopiException("Error saving tasks");
        }
    }
}
