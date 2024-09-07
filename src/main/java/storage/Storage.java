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
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

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

        // Assert that the file path is not null
        assert filePath != null : "File path should not be null";

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
            return tasks;
        }

        List<String> lines = Files.readAllLines(Paths.get(filePath));

        for (String line : lines) {
            String[] parts = line.split(" \\| ");
            String taskType = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            switch (taskType) {
                case "T":
                    tasks.add(new ToDo(description));
                    tasks.get(tasks.size() - 1).setStatus(isDone);
                    break;
                case "D":
                    LocalDateTime by = LocalDateTime.parse(parts[3], DATE_TIME_FORMATTER);
                    tasks.add(new DeadLine(description, by));
                    tasks.get(tasks.size() - 1).setStatus(isDone);
                    break;
                case "E":
                    String from = parts[3];
                    String to = parts[4];
                    tasks.add(new Event(description, from, to));
                    tasks.get(tasks.size() - 1).setStatus(isDone);
                    break;
                default:
                    throw new JarException("Data file corrupted. Invalid task type.");
            }
        }

        return tasks;
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
