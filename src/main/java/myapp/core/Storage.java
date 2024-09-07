package myapp.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import myapp.task.Deadline;
import myapp.task.Event;
import myapp.task.Task;
import myapp.task.TaskList;
import myapp.task.ToDo;


/**
 * The {@code Storage} class handles the loading and saving of tasks to and from a file.
 * It provides methods to read tasks from a file into a {@link TaskList} and save tasks
 * from a {@link TaskList} to a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a {@code Storage} object with the specified file path.
     *
     * @param filePath the file path where tasks will be loaded from and saved to.
     */
    public Storage(String filePath) {
        assert filePath != null : "File path should not be null";
        assert !filePath.isEmpty() : "File path should not be empty";
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the specified file.
     *
     * @return a list of {@link Task} objects loaded from the file.
     * @throws BingBongException if an error occurs while reading from the file
     *      or if the file contains unknown task types.
     */
    public List<Task> load() throws BingBongException {
        try {
            return Files.lines(Paths.get(filePath))
                    .map(line -> {
                        try {
                            return parseTask(line);
                        } catch (BingBongException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new BingBongException("Error reading from file: " + e.getMessage());
        }
    }

    /**
     * Parses a line from the file and returns the corresponding {@link Task} object.
     *
     * @param line The line read from the file.
     * @return The corresponding {@link Task} object.
     * @throws BingBongException If an unknown task type is encountered.
     */
    private Task parseTask(String line) throws BingBongException {
        String[] taskData = line.split(" \\| ");
        String type = taskData[0];
        boolean isDone = taskData[1].equals("1");
        String description = taskData[2];

        Task task;
        switch (type) {
        case "T":
            task = new ToDo(description);
            break;
        case "D":
            String by = taskData[3];
            task = new Deadline(description, DateTimeHandler.parse(by));
            break;
        case "E":
            String from = taskData[3];
            String to = taskData[4];
            task = new Event(description, DateTimeHandler.parse(from), DateTimeHandler.parse(to));
            break;
        default:
            throw new BingBongException("Unknown task type in file.");
        }

        if (isDone) {
            task.markAsDone();
        }

        return task;
    }

    /**
     * Saves the tasks in the given {@link TaskList} to the specified file.
     *
     * @param tasks the {@link TaskList} containing the tasks to be saved.
     * @throws BingBongException if an error occurs while writing to the file or if the directory cannot be created.
     */
    public void save(TaskList tasks) throws BingBongException {
        assert tasks != null : "TaskList should not be null";
        File file = new File(filePath);

        // Ensure the parent directory exists
        File directory = file.getParentFile();
        if (directory != null && !directory.exists()) {
            if (!directory.mkdirs()) {
                throw new BingBongException("Failed to create directory: " + directory);
            }
        }

        try (FileWriter writer = new FileWriter(filePath)) {
            tasks.stream()
                    .map(Task::toFileFormat)
                    .forEach(task -> writeTask(writer, task));
        } catch (IOException e) {
            throw new BingBongException("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Writes a task to the file.
     *
     * @param writer The {@link FileWriter} object to write to the file.
     * @param task The task in file format.
     */
    private void writeTask(FileWriter writer, String task) {
        try {
            writer.write(task + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Error writing task to file.", e);
        }
    }
}
