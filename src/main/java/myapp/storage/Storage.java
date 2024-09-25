package myapp.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import myapp.task.*;
import myapp.exception.RubyException;

/**
 * The {@code Storage} class handles reading from and writing to a file that stores tasks.
 * It allows tasks to be loaded into the application at startup and saved back to the file on updates.
 */
public class Storage {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    private final String filePath;

    /**
     * Constructs a {@code Storage} object with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified by the file path.
     * If the file does not exist, an empty list of tasks is returned.
     *
     * @return A list of tasks read from the file.
     * @throws IOException   If an I/O error occurs while reading from the file.
     * @throws RubyException If the file contains an invalid task type or format.
     */
    public List<Task> load() throws IOException, RubyException {
        Path path = Paths.get(filePath);
        File file = new File(filePath);
        File directory = file.getParentFile();
        if (directory != null && !directory.exists()) {
            directory.mkdirs();
        }
        if (!Files.exists(path)) {
            return new ArrayList<>();
        }

        List<String> lines = Files.readAllLines(path);
        return parseTasks(lines);
    }

    /**
     * Parses a list of lines from the file into tasks.
     *
     * @param lines The lines to be parsed into tasks.
     * @return A list of tasks parsed from the file.
     * @throws RubyException If the file contains an invalid task type or format.
     */
    private List<Task> parseTasks(List<String> lines) throws RubyException {
        List<Task> tasks = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(" \\| ");
            Task task = createTask(parts);
            assert task != null : "Task creation failed for line: " + line;
            if (isTaskDone(parts[1])) {
                task.markAsDone();
            }
            tasks.add(task);
        }
        return tasks;
    }

    /**
     * Creates a task object based on the task type and description in the file.
     *
     * @param parts The parts of a task string split by delimiters.
     * @return The created task.
     * @throws RubyException If the task type is invalid.
     */
    private Task createTask(String[] parts) throws RubyException {
        LocalDateTime now = LocalDateTime.now();
        switch (parts[0]) {
        case "T":
            return new Todo(parts[2]);
        case "D":
            LocalDateTime by = LocalDateTime.parse(parts[3], FORMATTER);
            if (by.isBefore(now)) {
                throw new RubyException("Deadline cannot be set in the past.");
            }
            return new Deadline(parts[2], by);
        case "E":
            LocalDateTime from = LocalDateTime.parse(parts[3], FORMATTER);
            LocalDateTime to = LocalDateTime.parse(parts[4], FORMATTER);
            if (to.isBefore(from)) {
                throw new RubyException("Event end time cannot be before the start time.");
            }
            return new Event(parts[2], from, to);
        default:
            throw new RubyException("Invalid task type.");
        }
    }

    /**
     * Checks if a task is marked as done.
     *
     * @param status The status part of the task string.
     * @return True if the task is marked as done, false otherwise.
     */
    private boolean isTaskDone(String status) {
        return status.equals("1");
    }

    /**
     * Saves the provided list of tasks to the file specified by the file path.
     * Overwrites the contents of the file with the current list of tasks.
     *
     * @param tasks The list of tasks to be saved to the file.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void save(List<Task> tasks) throws IOException {
        File file = new File(filePath);
        File directory = file.getParentFile();
        if (directory != null && !directory.exists()) {
            directory.mkdirs(); 
        }

        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks) {
                String taskString = formatTask(task);
                writer.write(taskString + "\n");
            }
        }
    }

    /**
     * Formats a task into a string to be saved to the file.
     *
     * @param task The task to be formatted.
     * @return The formatted task string.
     */
    private String formatTask(Task task) {
        String taskType = getTaskType(task);
        String dateTimeInfo = getDateTimeInfo(task);
        return String.format("%s | %s | %s%s",
                taskType,
                task.isDone() ? "1" : "0",
                task.getDescription(),
                dateTimeInfo.isEmpty() ? "" : " | " + dateTimeInfo
        );
    }

    /**
     * Returns the task type as a string based on the task instance.
     *
     * @param task The task object.
     * @return "T" for Todo, "D" for Deadline, "E" for Event.
     */
    private String getTaskType(Task task) {
        if (task instanceof Todo) {
            return "T";
        } else if (task instanceof Deadline) {
            return "D";
        } else if (task instanceof Event) {
            return "E";
        } else {
            throw new IllegalArgumentException("Unknown task type.");
        }
    }

    /**
     * Returns the date and time information for Deadline and Event tasks.
     *
     * @param task The task object.
     * @return A formatted string with date/time information for Deadline and Event tasks.
     */
    private String getDateTimeInfo(Task task) {
        if (task instanceof Deadline) {
            return ((Deadline) task).getBy().format(FORMATTER);
        } else if (task instanceof Event) {
            return ((Event) task).getFrom().format(FORMATTER) + " | " + ((Event) task).getTo().format(FORMATTER);
        }
        return "";
    }
}