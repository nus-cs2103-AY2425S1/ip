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
     */
    protected void saveToFile() {
        assert rootPath != null && !rootPath.isEmpty() : "rootPath must not be null or empty";
        assert tasks != null : "Task list must not be null";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rootPath))) {
            writeTasksToFile(writer);
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
     * Writes the list of tasks to the file using the writer.
     *
     * @param writer The BufferedWriter to write the tasks to the file.
     */
    private void writeTasksToFile(BufferedWriter writer) throws IOException {
        for (Task task : tasks) {
            writer.write(task.toSaveFormat());
            writer.newLine();
        }
    }

    /**
     * Loads tasks from the specified file.
     * If the file does not exist, no action is taken.
     */
    protected void loadFromFile() {
        assert rootPath != null && !rootPath.isEmpty() : "rootPath must not be null or empty";

        File file = new File(rootPath);
        if (!file.exists()) {
            return;  // No file to load from
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            readTasksFromFile(reader);

        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }

    /**
     * Reads tasks from the file and adds them to the task list.
     *
     * @param reader The BufferedReader to read tasks from the file.
     */
    private void readTasksFromFile(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            Task task = parseTask(line);
            if (task != null) {
                tasks.add(task);
            }
        }
    }

    /**
     * Parses a line from the file to create the corresponding task.
     *
     * @param line The line from the file representing a task.
     * @return The created Task object, or null if parsing fails.
     */
    private Task parseTask(String line) {
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

        if (task != null && isDone) {
            task.markAsDone();
        }

        return task;
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
