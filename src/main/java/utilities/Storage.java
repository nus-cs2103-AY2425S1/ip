package utilities;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * The Storage class handles loading and saving tasks to a file.
 * It reads from and writes to a specified file to maintain task persistence.
 */
public class Storage {

    /** Path to the file where tasks are stored. */
    public String rootPath;
    /** List of tasks to be saved or loaded. */
    private TaskList tasks = new TaskList();
    /** Formatter used for parsing and formatting date-time values. */
    private final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    /**
     * Constructs a Storage object with the specified filename.
     *
     * @param filename The file path where tasks will be saved and loaded from.
     */
    public Storage(String filename) {
        assert filename != null && !filename.isEmpty() : "Filename must not be null or empty";
        this.rootPath = filename;
    }

    /**
     * Saves the current task list to the specified file.
     * Each task is written in a format suitable for loading later.
     */
    protected void saveToFile() {
        assert rootPath != null && !rootPath.isEmpty() : "rootPath must not be null or empty";
        assert tasks != null : "Task list must not be null";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rootPath))) {
            for (Task task : tasks) {
                assert task != null : "Task must not be null";
                writer.write(task.toSaveFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the specified file. Each task is parsed from the saved format
     * and added to the task list. If the file does not exist, no action is taken.
     */
    protected void loadFromFile() {
        assert rootPath != null && !rootPath.isEmpty() : "rootPath must not be null or empty";

        File file = new File(rootPath);
        if (!file.exists()) {
            return;  // No file to load from
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                assert parts.length >= 3 : "Invalid task format";

                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];
                assert description != null && !description.isEmpty() : "Task description must not be null or empty";

                Task task = null;
                switch (type) {
                case "T":
                    task = new Todo(description);
                    break;
                case "D":
                    assert parts.length == 4 : "Deadline format must contain 4 parts";
                    LocalDateTime by = LocalDateTime.parse(parts[3], DATE_FORMATTER);
                    task = new Deadline(description, by);
                    break;
                case "E":
                    assert parts.length == 5 : "Event format must contain 5 parts";
                    LocalDateTime from = LocalDateTime.parse(parts[3], DATE_FORMATTER);
                    LocalDateTime to = LocalDateTime.parse(parts[4], DATE_FORMATTER);
                    task = new Event(description, from, to);
                    break;
                default:
                    assert false : "Unknown task type";
                }

                if (task != null) {
                    if (isDone) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks currently loaded.
     */
    protected ArrayList<Task> getTasks() {
        assert tasks != null : "Task list must not be null";
        return this.tasks;
    }
}
