package myapp.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import myapp.task.Deadline;
import myapp.task.Event;
import myapp.task.FixedDuration;
import myapp.task.Task;
import myapp.task.TaskList;
import myapp.task.ToDo;


/**
 * The {@code Storage} class handles the loading and saving of tasks to and from a file.
 * It provides methods to read tasks from a file into a {@link TaskList} and save tasks
 * from a {@link TaskList} to a file. It supports the task types {@link ToDo}, {@link Deadline},
 * {@link Event}, and {@link FixedDuration}.
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
     * @return a list of {@link Task} objects loaded from the file, which can include
     *         {@link ToDo}, {@link Deadline}, {@link Event}, and {@link FixedDuration} tasks.
     * @throws BingBongException if an error occurs while reading from the file
     *      or if the file contains unknown task types.
     */
    public List<Task> load() throws BingBongException {
        List<Task> tasks = new ArrayList<>();
        try (Scanner reader = new Scanner(new File(filePath))) {
            while (reader.hasNext()) {
                String line = reader.nextLine();
                Task task = parseTask(line);
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new BingBongException("Error reading from file: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the tasks in the given {@link TaskList} to the specified file.
     *
     * @param tasks the {@link TaskList} containing the tasks to be saved.
     * @throws BingBongException if an error occurs while writing to the file or if the directory cannot be created.
     */
    public void save(TaskList tasks) throws BingBongException {
        assert tasks != null : "TaskList should not be null";
        ensureDirectoryExists();
        writeTasksToFile(tasks);
    }

    /**
     * Ensures that the directory for the file path exists. If the directory does not exist,
     * it attempts to create it.
     *
     * @throws BingBongException if the directory cannot be created.
     */
    private void ensureDirectoryExists() throws BingBongException {
        File file = new File(filePath);
        File directory = file.getParentFile();
        if (directory != null && !directory.exists()) {
            if (!directory.mkdirs()) {
                throw new BingBongException("Failed to create directory: " + directory);
            }
        }
    }

    /**
     * Parses a line from the file and creates a corresponding {@link Task} object.
     *
     * @param line the line from the file representing a task.
     * @return the {@link Task} object created from the parsed line, which can be one of
     *         {@link ToDo}, {@link Deadline}, {@link Event}, or {@link FixedDuration}.
     * @throws BingBongException if the task type is unknown or if the data is invalid.
     */
    private Task parseTask(String line) throws BingBongException {
        String[] taskData = line.split(" \\| ");
        String type = taskData[0];
        boolean isDone = taskData[1].equals("1");
        String description = taskData[2];

        Task task = createTask(type, taskData, description);

        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Creates a {@link Task} object based on the type provided in the task data.
     *
     * @param type the type of task ("T" for {@link ToDo}, "D" for {@link Deadline},
     *             "E" for {@link Event}, "F" for {@link FixedDuration}).
     * @param taskData the data for the task being created.
     * @param description the description of the task.
     * @return the created {@link Task} object.
     * @throws BingBongException if the task type is unknown.
     */
    private Task createTask(String type, String[] taskData, String description) throws BingBongException {
        switch (type) {
        case "T":
            return new ToDo(description);
        case "D":
            return createDeadlineTask(taskData, description);
        case "E":
            return createEventTask(taskData, description);
        case "F":
            return createFixedDurationTask(taskData, description);
        default:
            throw new BingBongException("Unknown task type in file.");
        }
    }

    /**
     * Creates a {@link Deadline} task from the given task data and description.
     *
     * @param taskData the data for the {@link Deadline} task.
     * @param description the description of the {@link Deadline} task.
     * @return the created {@link Deadline} object.
     * @throws BingBongException if there is an error parsing the deadline date.
     */
    private Deadline createDeadlineTask(String[] taskData, String description) throws BingBongException {
        String by = taskData[3];
        return new Deadline(description, DateTimeHandler.parse(by));
    }

    /**
     * Creates an {@link Event} task from the given task data and description.
     *
     * @param taskData the data for the {@link Event} task.
     * @param description the description of the {@link Event} task.
     * @return the created {@link Event} object.
     * @throws BingBongException if there is an error parsing the event dates.
     */
    private Event createEventTask(String[] taskData, String description) throws BingBongException {
        String from = taskData[3];
        String to = taskData[4];
        return new Event(description, DateTimeHandler.parse(from), DateTimeHandler.parse(to));
    }

    /**
     * Creates a {@link FixedDuration} task from the given task data and description.
     *
     * @param taskData the data for the {@link FixedDuration} task.
     * @param description the description of the {@link FixedDuration} task.
     * @return the created {@link FixedDuration} object.
     * @throws BingBongException if there is an error parsing the duration.
     */
    private FixedDuration createFixedDurationTask(String[] taskData, String description) throws BingBongException {
        int hours = Integer.parseInt(taskData[3]);
        return new FixedDuration(description, hours);
    }

    /**
     * Writes the tasks in the given {@link TaskList} to the file.
     *
     * @param tasks the {@link TaskList} containing the tasks to be written.
     * @throws BingBongException if an error occurs while writing to the file.
     */
    private void writeTasksToFile(TaskList tasks) throws BingBongException {
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

