package orion.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import orion.orionExceptions.FileInitializationException;
import orion.task.Deadline;
import orion.task.Event;
import orion.task.Task;
import orion.task.Todo;

/**
 * Handles the storage of tasks by reading from and writing to a CSV file.
 *
 * <p>
 * This class is responsible for initializing the storage file, reading tasks
 * from
 * the file into memory, and writing tasks from memory to the file.
 * </p>
 */
public class Storage {
    private static final String DATA_FILE_PATH = "./data/tasks.csv";

    /**
     * Constructs a Storage object and initializes the storage file.
     *
     * @throws FileInitializationException if there is an issue with file
     *                                     initialization.
     */
    public Storage() throws FileInitializationException {
        initializeFile();
    }

    /**
     * Initializes the storage file by creating it if it does not exist.
     *
     * <p>
     * This method also ensures that the necessary directories are created if they
     * do not exist.
     * </p>
     *
     * @throws FileInitializationException if there is an issue creating directories
     *                                     or the file.
     */
    private void initializeFile() throws FileInitializationException {
        File dataFile = new File(DATA_FILE_PATH);
        try {
            if (!dataFile.exists()) {
                if (dataFile.getParentFile() != null && !dataFile.getParentFile().exists()) {
                    boolean dirsCreated = dataFile.getParentFile().mkdirs();
                    if (!dirsCreated) {
                        throw new FileInitializationException(
                                "Failed to create directories: " + dataFile.getParentFile());
                    }
                }

                boolean fileCreated = dataFile.createNewFile();
                if (!fileCreated) {
                    throw new FileInitializationException("Failed to create file: " + dataFile.getPath());
                }
            }
        } catch (IOException e) {
            throw new FileInitializationException("IOException occurred: " + e.getMessage());
        }
    }

    /**
     * Reads tasks from the storage file and loads them into a list.
     *
     * <p>
     * Each line in the file represents a task with details like ID, type,
     * description,
     * completion status, and potentially dates. Tasks are parsed and added to the
     * list.
     * </p>
     *
     * @return a list of tasks read from the file.
     * @throws FileInitializationException if there is an issue reading the file or
     *                                     parsing its contents.
     */
    public List<Task> read() throws FileInitializationException {
        List<Task> loadedTasks = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(DATA_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int taskId = Integer.parseInt(parts[0]);
                String type = parts[1];
                String description = parts[2];
                boolean completed = Boolean.parseBoolean(parts[3]);

                Task task = null;
                switch (type) {
                    case "TODO":
                        task = new Todo(taskId, description);
                        break;
                    case "DEADLINE":
                        LocalDateTime by = LocalDateTime.parse(parts[4]);
                        task = new Deadline(taskId, description, by);
                        break;
                    case "EVENT":
                        String[] eventTimes = parts[4].split("\\|");
                        LocalDateTime from = LocalDateTime.parse(eventTimes[0]);
                        LocalDateTime to = LocalDateTime.parse(eventTimes[1]);
                        task = new Event(taskId, description, from, to);
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown task type: " + type);
                }

                task.setCompleted(completed);
                loadedTasks.add(task);
            }
        } catch (IOException e) {
            throw new FileInitializationException("Error reading from file: " + e.getMessage());
        } catch (DateTimeParseException | NumberFormatException e) {
            throw new FileInitializationException("Error parsing task data: " + e.getMessage());
        }

        return loadedTasks;
    }

    /**
     * Writes a list of tasks to the storage file.
     *
     * <p>
     * Each task is written as a line in the file with details like ID, type,
     * description,
     * completion status, and dates if applicable.
     * </p>
     *
     * @param tasks the list of tasks to write to the file.
     * @throws FileInitializationException if there is an issue writing to the file.
     */
    public void write(List<Task> tasks) throws FileInitializationException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DATA_FILE_PATH))) {
            for (Task task : tasks) {
                StringBuilder taskLine = new StringBuilder();
                taskLine.append(task.getTaskID()).append(",");
                taskLine.append(getTaskType(task)).append(",");
                taskLine.append(task.getDescription()).append(",");
                taskLine.append(task.isCompleted());

                if (task instanceof Deadline) {
                    taskLine.append(",").append(((Deadline) task).getBy());
                } else if (task instanceof Event) {
                    taskLine.append(",").append(((Event) task).getFrom())
                            .append("|").append(((Event) task).getTo());
                }

                bw.write(taskLine.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new FileInitializationException("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Gets the type of a task as a string.
     *
     * @param task the task for which the type is to be determined.
     * @return a string representing the type of the task (e.g., "TODO", "DEADLINE",
     *         "EVENT").
     * @throws IllegalArgumentException if the task type is unknown.
     */
    private String getTaskType(Task task) {
        if (task instanceof Todo) {
            return "TODO";
        } else if (task instanceof Deadline) {
            return "DEADLINE";
        } else if (task instanceof Event) {
            return "EVENT";
        } else {
            throw new IllegalArgumentException("Unknown task type: " + task.getClass().getName());
        }
    }
}
