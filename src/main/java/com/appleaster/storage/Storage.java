package com.appleaster.storage;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.appleaster.exception.AppleasterException;
import com.appleaster.task.Deadline;
import com.appleaster.task.Event;
import com.appleaster.task.Task;
import com.appleaster.task.Todo;

/**
 * Handles the loading and saving of tasks to a file.
 * This class is responsible for persisting task data between application sessions.
 */
public class Storage {
    private static final String DATA_DIRECTORY = "data";
    private final Path filePath;
    private static final DateTimeFormatter FILE_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath the path to the file where tasks will be stored
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    /**
     * Loads tasks from the file.
     *
     * @return a list of tasks read from the file
     * @throws AppleasterException if an error occurs while loading the tasks
     */
    public List<Task> load() throws AppleasterException {
        try {
            createDataDirectoryIfNotExists();
            if (!Files.exists(filePath)) {
                return new ArrayList<>();
            }

            List<Task> tasks = new ArrayList<>();
            try (BufferedReader reader = Files.newBufferedReader(filePath)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    tasks.add(parseTask(line));
                }
            }
            return tasks;
        } catch (IOException e) {
            throw new AppleasterException("Error loading tasks: " + e.getMessage());
        }
    }

    /**
     * Saves the given list of tasks to the file.
     *
     * @param tasks the list of tasks to be saved
     * @throws AppleasterException if an error occurs while saving the tasks
     */
    public void save(List<Task> tasks) throws AppleasterException {
        try {
            createDataDirectoryIfNotExists();
            try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
                for (Task task : tasks) {
                    writer.write(formatTask(task));
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            throw new AppleasterException("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Creates the data directory if it doesn't exist.
     *
     * @throws IOException if an I/O error occurs
     */
    private void createDataDirectoryIfNotExists() throws IOException {
        Files.createDirectories(filePath.getParent());
    }

    /**
     * Formats a task into a string for file storage.
     *
     * @param task the task to be formatted
     * @return a string representation of the task
     * @throws IllegalArgumentException if the task type is unknown
     */
    private String formatTask(Task task) {
        if (task instanceof Todo) {
            return String.format("T | %d | %s", task.isCompleted() ? 1 : 0, task.getDescription());
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return String.format("D | %d | %s | %s", task.isCompleted() ? 1 : 0, task.getDescription(), deadline.getBy().format(FILE_DATE_FORMAT));
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return String.format("E | %d | %s | %s | %s", task.isCompleted() ? 1 : 0, task.getDescription(), 
                                 event.getFrom().format(FILE_DATE_FORMAT), event.getTo().format(FILE_DATE_FORMAT));
        }
        throw new IllegalArgumentException("Unknown task type");
    }

    /**
     * Parses a string from the file into a Task object.
     *
     * @param line the string to be parsed
     * @return the Task object created from the string
     * @throws IllegalArgumentException if the task type is unknown
     */
    private Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isCompleted = parts[1].equals("1");
        String description = parts[2];

        Task task;
        switch (type) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                task = new Deadline(description, parts[3]);
                break;
            case "E":
                task = new Event(description, parts[3], parts[4]);
                break;
            default:
                throw new IllegalArgumentException("Unknown task type: " + type);
        }

        if (isCompleted) {
            task.markAsDone();
        }
        return task;
    }
}