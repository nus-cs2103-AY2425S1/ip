package streams.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import streams.exception.StreamsException;
import streams.task.DeadlineTask;
import streams.task.EventTask;
import streams.task.Task;
import streams.task.ToDoTask;

/**
 * Handles loading tasks from and saving tasks to a file.
 */
public class Storage {
    private static String FILE_PATH;
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path of the file to store the tasks.
     */
    public Storage(String filePath) {
        assert filePath != null && !filePath.trim().isEmpty() : "File path cannot be null or empty";
        FILE_PATH = filePath;
    }

    /**
     * Loads tasks from the file.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws StreamsException If there's an error reading the file.
     */
    public ArrayList<Task> load() throws StreamsException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);
        File directory = file.getParentFile();

        assert directory != null : "Parent directory should not be null";
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                throw new StreamsException("failed to create directory: " + directory.getAbsolutePath());
            }
        }

        if (!file.exists()) {
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                assert parts.length >= 3 : "Task string should have at least 3 parts";
                boolean isDone = parts[1].equals("1");
                String description = parts[2];
                Task task;
                switch (parts[0]) {
                    case "T":
                        task = new ToDoTask(description);
                        break;
                    case "D":
                        assert parts.length >= 4 : "Deadline task should have at least 4 parts";
                        LocalDateTime by = LocalDateTime.parse(parts[3], INPUT_FORMATTER);
                        task = new DeadlineTask(description, by);
                        break;
                    case "E":
                        assert parts.length >= 5 : "Event task should have at least 5 parts";
                        LocalDateTime from = LocalDateTime.parse(parts[3], INPUT_FORMATTER);
                        LocalDateTime to = LocalDateTime.parse(parts[4], INPUT_FORMATTER);
                        task = new EventTask(description, from, to);
                        break;
                    default:
                        continue;
                }
                if (isDone) {
                    task.markAsDone();
                }
                tasks.add(task);
            }
        } catch (IOException | DateTimeParseException e) {
            throw new StreamsException("an error occurred while reading the file: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves tasks to the file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws StreamsException If there's an error writing to the file.
     */
    public void save(ArrayList<Task> tasks) throws StreamsException {
        assert tasks != null : "Tasks list cannot be null";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                String taskType = task instanceof ToDoTask ? "T"
                        : task instanceof DeadlineTask ? "D"
                        : task instanceof EventTask ? "E" : "";
                String isDone = task.isCompleted() ? "1" : "0";
                String taskString = taskType + " | " + isDone + " | " + task.getDescription();

                if (task instanceof DeadlineTask) {
                    taskString += " | " + ((DeadlineTask) task).getBy().format(INPUT_FORMATTER);
                } else if (task instanceof EventTask) {
                    taskString += " | " + ((EventTask) task).getFrom().format(INPUT_FORMATTER)
                            + " | " + ((EventTask) task).getTo().format(INPUT_FORMATTER);
                }

                writer.write(taskString);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new StreamsException("an error occurred while saving the tasks: " + e.getMessage());
        }
    }
}
