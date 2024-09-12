package Alex.Storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Alex.Exceptions.AlexException;
import Alex.Task.Deadline;
import Alex.Task.Event;
import Alex.Task.Task;
import Alex.Task.TaskType;
import Alex.Task.Todo;

/**
 * Manages the loading and saving of tasks to and from a file.
 * Ensures that tasks are correctly persisted and restored from storage.
 */
public class Storage {

    private String filePath;

    // DateTimeFormatter used for formatting Deadline and Event times.
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Constructs a Storage object with the given file path.
     * Initializes the data directory if it does not exist.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        createDataDirectory();
    }

    /**
     * Loads tasks from the file specified by the file path.
     * Parses each line to create Task objects and adds them to an ArrayList.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws AlexException If there is an error loading tasks from the file.
     */
    public ArrayList<Task> load() throws AlexException {
        File file = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<>();
        if (!file.exists()) {
            return tasks; // Return an empty list if file doesn't exist
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Task task = parseTaskLine(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException | IllegalArgumentException e) {
            throw new AlexException("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Parses a line from the file to create a Task object.
     *
     * @param line A line from the file representing a task.
     * @return A Task object created from the line, or null if the line is invalid.
     * @throws AlexException If the task type is unexpected.
     */
    private Task parseTaskLine(String line) throws AlexException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            return null; // Skip invalid lines with insufficient data
        }

        TaskType type = TaskType.valueOf(parts[0]);
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task = createTask(type, description, parts);
        if (task != null && isDone) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Creates a Task object based on the type and data provided.
     *
     * @param type The type of task to create (TODO, DEADLINE, EVENT).
     * @param description The description of the task.
     * @param parts The split line parts containing additional task details.
     * @return A Task object of the specified type, or null if the data is insufficient.
     * @throws AlexException If the task type is unexpected.
     */
    private Task createTask(TaskType type, String description, String[] parts) throws AlexException {
        switch (type) {
        case TODO:
            return new Todo(description);
        case DEADLINE:
            if (parts.length == 4) {
                return new Deadline(description, parts[3]);
            }
            break;
        case EVENT:
            if (parts.length == 5) {
                return new Event(description, parts[3], parts[4]);
            }
            break;
        default:
            throw new AlexException("Unexpected task type: " + type);
        }
        return null;
    }

    /**
     * Saves the given tasks to the file specified by the file path.
     * Formats each task into a string and writes it to the file.
     *
     * @param tasks The ArrayList of tasks to save to the file.
     * @throws AlexException If there is an error saving tasks to the file.
     */
    public void save(ArrayList<Task> tasks) throws AlexException {
        File file = new File(filePath);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                String line = formatTaskLine(task);
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new AlexException("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Formats a Task object into a string representation for saving.
     *
     * @param task The Task object to format.
     * @return A string representation of the task.
     */
    private String formatTaskLine(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append(task.getTaskType()).append(" | ");
        sb.append(task.isDone ? "1" : "0").append(" | ");
        sb.append(task.getDescription());

        if (task instanceof Deadline) {
            sb.append(" | ").append(((Deadline) task).by.format(DATE_TIME_FORMATTER));
        } else if (task instanceof Event) {
            sb.append(" | ").append(((Event) task).getStarttime())
                    .append(" | ").append(((Event) task).getEndtime());
        }
        return sb.toString();
    }

    /**
     * Creates the data directory if it does not already exist.
     * This ensures that the directory structure is in place for storing task files.
     */
    private void createDataDirectory() {
        File dataDir = new File("./data");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }
    }
}

