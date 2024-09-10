package fanny.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fanny.task.Deadline;
import fanny.task.Event;
import fanny.task.Task;
import fanny.task.ToDo;


/**
 * Handles the loading and saving of tasks to a file.
 */
public class Storage {

    /** The file path where the tasks are stored. */
    private String filePath;

    /**
     * Constructs a {@code Storage} object with the specified file path.
     *
     * @param filePath The file path where tasks will be loaded from and saved to.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file at the specified file path.
     *
     * @return A list of tasks loaded from the file.
     * @throws FileNotFoundException If the file does not exist.
     */
    public List<Task> loadTask() throws FileNotFoundException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseTask(line);
                tasks.add(task);
            }
        }

        return tasks;
    }

    /**
     * Saves the list of tasks to the file at the specified file path.
     *
     * @param tasks The list of tasks to be saved.
     * @throws IOException If an I/O error occurs during saving.
     */
    public void save(List<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for (Task task : tasks) {
            writer.write(taskToString(task) + "\n");
        }
        writer.close();
    }

    /**
     * Parses a task from a line of text in the file.
     *
     * @param line The line of text representing a task.
     * @return The task parsed from the line.
     * @throws IllegalArgumentException If the task type in the line is unknown.
     */
    private Task parseTask(String line) throws IllegalArgumentException {
        String[] parts = line.split("\\|");
        String taskType = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");

        Task task;
        switch (taskType) {
        case "T":
            task = new ToDo(description);
            break;
        case "D":
            String by = parts[3].trim();
            LocalDateTime byDate = LocalDateTime.parse(parts[3].trim(), formatter);
            task = new Deadline(description, byDate);
            break;
        case "E":
            String from = parts[3].trim();
            String to = parts[4].trim();
            LocalDateTime fromDate = LocalDateTime.parse(parts[3].trim(), formatter);
            LocalDateTime toDate = LocalDateTime.parse(parts[4].trim(), formatter);
            task = new Event(description, fromDate, toDate);
            break;
        default:
            throw new IllegalArgumentException("Unknown task type");
        }

        if (isDone) {
            task.markAsDone();
        }

        return task;
    }

    /**
     * Converts a task to a string suitable for saving to a file.
     *
     * @param task The task to be converted.
     * @return A string representation of the task.
     */
    private String taskToString(Task task) {
        String taskType = task instanceof ToDo ? "T" : task instanceof Deadline ? "D" : "E";
        String isDone = task.getStatusIcon().equals("X") ? "1" : "0";
        String description = task.getDescription();
        String extra = "";

        if (task instanceof Deadline) {
            extra = "|" + ((Deadline) task).getDeadline();
        } else if (task instanceof Event) {
            extra = "|" + ((Event) task).getStartTime() + "|" + ((Event) task).getEndTime();
        }

        return taskType + " | " + isDone + " | " + description + extra;
    }


}
