package asta;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import asta.task.Deadline;
import asta.task.Event;
import asta.task.Task;
import asta.task.ToDo;

/**
 * The Storage class handles the reading and writing of task data to a file. It provides methods to load tasks from a
 * file at startup and save tasks to a file during runtime.
 */

public class Storage {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path of the file where tasks will be saved and loaded from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the specified file. If the file does not exist, throws an exception. It reads each line of the
     * file, interprets the task data, and populates the task list.
     *
     * @return A list of tasks loaded from the file.
     * @throws AstaException If the file does not exist, the task format is invalid, or there is an error reading the
     *                       file.
     */
    public ArrayList<Task> load() throws AstaException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            throw new AstaException("No existing data file found.");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                if (parts.length < 3) {
                    throw new AstaException("Invalid task format: " + line);
                }

                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];
                Task task;

                switch (type) {
                case "T":
                    task = new ToDo(description, isDone);
                    break;
                case "D":
                    LocalDateTime by = LocalDateTime.parse(parts[3].trim(), DATE_TIME_FORMATTER);
                    task = new Deadline(description, by, isDone);
                    break;
                case "E":
                    LocalDateTime from = LocalDateTime.parse(parts[3].trim(), DATE_TIME_FORMATTER);
                    LocalDateTime to = LocalDateTime.parse(parts[4].trim(), DATE_TIME_FORMATTER);
                    task = new Event(description, from, to, isDone);
                    break;
                default:
                    throw new AstaException("Unknown task type: " + type);
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new AstaException("Error loading tasks from file: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Saves the given list of tasks to the specified file. It writes each task's data to a line in the file.
     *
     * @param tasks The list of tasks to save.
     * @throws AstaException If there is an error writing to the file.
     */
    public void save(ArrayList<Task> tasks) throws AstaException {
        File file = new File(filePath);
        file.getParentFile().mkdirs();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                assert task != null : "Task is null in task list";

                writer.write(task.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new AstaException("Error saving tasks to file: " + e.getMessage());
        }
    }
}
