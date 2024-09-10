package storage;

import tasks.DeadLine;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;
import exceptions.JarException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * The Storage class handles the reading and writing of tasks to a file.
 * It is responsible for loading tasks from a file into the application and
 * saving the current tasks back to the file.
 */
public class Storage {
    private String filePath;
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the specified file.
     * If the file does not exist, it creates a new file and returns an empty task list.
     *
     * @return A list of tasks loaded from the file.
     * @throws IOException  If an I/O error occurs while reading the file.
     * @throws JarException If the file contains corrupted data.
     */
    public List<Task> load() throws IOException, JarException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        assert filePath != null : "File path should not be null";

        if (!file.exists()) {
            createNewFile(file);
            return tasks;
        }

        List<String> lines = Files.readAllLines(Paths.get(filePath));
        for (String line : lines) {
            Task task = parseTaskFromLine(line);
            tasks.add(task);
        }

        return tasks;
    }

    /**
     * Creates a new file if the file does not exist.
     *
     * @param file The file to create.
     * @throws IOException If an I/O error occurs while creating the file.
     */
    private void createNewFile(File file) throws IOException {
        file.getParentFile().mkdirs();
        file.createNewFile();
    }

    /**
     * Parses a task from a line of text from the data file.
     *
     * @param line A line of text from the data file representing a task.
     * @return The parsed Task object.
     * @throws JarException If the task type is invalid or the data is corrupted.
     */
    private Task parseTaskFromLine(String line) throws JarException {
        String[] parts = line.split(" \\| ");

        String taskType = parts[0];
        boolean isDone = parseTaskStatus(parts[1]);
        String description = parts[2];

        Task task = createTaskFromType(taskType, description, parts);
        task.setStatus(isDone);

        return task;
    }

    /**
     * Parses the task's completion status from the string.
     *
     * @param status The status of the task ("1" for completed, "0" for not completed).
     * @return A boolean indicating whether the task is completed.
     */
    private boolean parseTaskStatus(String status) {
        return status.equals("1");
    }

    /**
     * Creates a task object based on the task type.
     *
     * @param taskType The type of the task ("T", "D", "E").
     * @param description The description of the task.
     * @param parts The array containing task details.
     * @return The created Task object.
     * @throws JarException If the task type is invalid or data is corrupted.
     */
    private Task createTaskFromType(String taskType, String description, String[] parts) throws JarException {
        switch (taskType) {
        case "T":
            return new ToDo(description);
        case "D":
            LocalDateTime by = LocalDateTime.parse(parts[3], DATE_TIME_FORMATTER);
            return new DeadLine(description, by);
        case "E":
            String from = parts[3];
            String to = parts[4];
            return new Event(description, from, to);
        default:
            throw new JarException("Data file corrupted. Invalid task type.");
        }
    }

    /**
     * Saves the current list of tasks to the specified file.
     *
     * @param tasks The list of tasks to save.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void save(List<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for (Task task : tasks) {
            writer.write(task.toFileFormat() + System.lineSeparator());
        }
        writer.close();
    }
}
