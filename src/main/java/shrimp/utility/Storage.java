package shrimp.utility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

import shrimp.exception.ShrimpException;
import shrimp.task.Deadline;
import shrimp.task.Event;
import shrimp.task.Task;
import shrimp.task.TaskList;
import shrimp.task.Todo;

/**
 * The {@code Storage} class handles saving and loading tasks to and from a file.
 * It provides methods to save the current list of tasks and load tasks from a file.
 */
public class Storage {
    private static final String FILE_PATH = "./data/shrimp.txt";

    /**
     * Saves the list of tasks to a file.
     *
     * @param tasks The {@code TaskList} containing the tasks to be saved.
     */
    public static void saveTasks(TaskList tasks) {
        assert tasks != null : "tasks is null";
        File file = new File(FILE_PATH);
        file.getParentFile().mkdirs(); // Create the directory if it doesn't exist

        try (FileWriter writer = new FileWriter(file)) {
            for (int i = 0; i < tasks.getCount(); i++) {
                Task task = tasks.getTask(i);
                writer.write(formatTaskForSaving(task) + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Loads tasks from a file and returns them as a {@code TaskList}.
     *
     * @return A {@code TaskList} containing the tasks loaded from the file.
     * @throws IOException     If an I/O error occurs while reading the file.
     * @throws ShrimpException If an error occurs while parsing the tasks.
     */
    public static TaskList loadTasks() throws IOException, ShrimpException {
        TaskList tasks = new TaskList();
        assert FILE_PATH != null : "file path is null";
        File file = new File(FILE_PATH);

        if (file.exists()) {
            List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));
            for (String line : lines) {
                Task task = parseTask(line);
                tasks.addTask(task);
            }
        }
        return tasks;
    }

    /**
     * Formats a {@code Task} object as a string suitable for saving to a file.
     *
     * @param task The {@code Task} to be formatted.
     * @return A string representation of the task.
     */
    private static String formatTaskForSaving(Task task) {
        assert task != null : "task is null";
        String type = task.getType();
        switch (type) {
        case "[T]" -> {
            return String.format("T | %d | %s", task.isDone() ? 1 : 0, task.getDescription());
        }
        case "[D]" -> {
            Deadline deadline = (Deadline) task;
            return String.format("D | %d | %s | %s", task.isDone() ? 1 : 0, task.getDescription(), deadline.getBy());
        }
        case "[E]" -> {
            Event event = (Event) task;
            return String.format("E | %d | %s | %s | %s", task.isDone() ? 1 : 0, task.getDescription(),
                    event.getFrom(), event.getTo());
        }
        default -> {
            return "";
        }
        }
    }

    /**
     * Parses a string representation of a task and returns a {@code Task} object.
     *
     * @param line The string representation of the task.
     * @return The parsed {@code Task} object.
     * @throws ShrimpException If an error occurs while parsing the task.
     */
    private static Task parseTask(String line) throws ShrimpException {
        assert line != null : "input string is null";
        String[] parts = line.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (taskType) {
        case "T":
            return new Todo(description, isDone);
        case "D":
            LocalDateTime by = getDateTime(parts[3]);
            return new Deadline(description, by, isDone);
        case "E":
            LocalDateTime from = getDateTime(parts[3]);
            LocalDateTime to = getDateTime(parts[4]);
            return new Event(description, from, to, isDone);
        default:
            throw new IllegalStateException("Unexpected value: " + taskType);
        }
    }

    /**
     * Parses a date-time string into a {@code LocalDateTime} object using the {@code Parser.PATTERN}.
     *
     * @param input The date-time string to be parsed.
     * @return The parsed {@code LocalDateTime} object.
     * @throws ShrimpException If the date-time string is invalid.
     */
    private static LocalDateTime getDateTime(String input) throws ShrimpException {
        assert input != null : "input string is null";
        try {
            return LocalDateTime.parse(input, Parser.PATTERN);
        } catch (DateTimeParseException e) {
            throw new ShrimpException.InvalidDateTimeException();
        }
    }
}
