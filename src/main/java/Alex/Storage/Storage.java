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
import Alex.Task.DefaultTask;
import Alex.Task.Event;
import Alex.Task.FixedDurationTask;
import Alex.Task.Task;
import Alex.Task.TaskType;
import Alex.Task.Todo;



/**
 * Manages the loading and saving of tasks from/to a file.
 */
public class Storage {

    private final String filePath;

    /**
     * Constructs a Storage object with the given file path.
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        createDataDirectory();
    }

    /**
     * Loads tasks from the file specified by the file path.
     * @return An ArrayList of tasks loaded from the file.
     * @throws AlexException If there is an error loading tasks from the file.
     */
    public ArrayList<Task> load() throws AlexException {
        File file = new File(filePath);
        if (!file.exists()) {
            return new ArrayList<>(); // No tasks to load if file doesn't exist
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            return loadTasksFromFile(br);
        } catch (IOException | IllegalArgumentException e) {
            throw new AlexException("Error loading tasks: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the BufferedReader.
     * @param br The BufferedReader to read from.
     * @return An ArrayList of tasks loaded from the file.
     * @throws IOException If there is an error reading from the file.
     */
    private ArrayList<Task> loadTasksFromFile(BufferedReader br) throws IOException, AlexException {
        ArrayList<Task> tasks = new ArrayList<>();
        String line;

        while ((line = br.readLine()) != null) {
            Task task = parseTaskFromLine(line);
            if (task != null) {
                tasks.add(task);
            }
        }
        return tasks;
    }

    /**
     * Parses a task from a line of text.
     * @param line The line to parse.
     * @return The parsed Task object, or null if the line is invalid.
     */
    private Task parseTaskFromLine(String line) throws AlexException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 2) {
            return null; // Skip invalid lines
        }

        TaskType type = TaskType.valueOf(parts[0]);
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task = createTaskFromParts(type, description, parts);
        if (task != null && isDone) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Creates a task object based on the type and parts from the file.
     * @param type The type of task (TODO, DEADLINE, EVENT).
     * @param description The task description.
     * @param parts Additional information for the task (such as deadlines or event times).
     * @return The created Task object, or null if invalid.
     */
    private Task createTaskFromParts(TaskType type, String description, String[] parts) throws AlexException {
        switch (type) {
        case TODO:
            return new Todo(description);
        case DEADLINE:
            return parts.length == 4 ? new Deadline(description, parts[3]) : null;
        case EVENT:
            return parts.length == 5 ? new Event(description, parts[3], parts[4]) : null;
        case DEFAULT:
            // Default tasks may only have the description, so no need for additional parts
            return new DefaultTask(description); // Assuming DefaultTask class exists
        case FIXED_DURATION:
            // Assuming the FixedDurationTask takes description and duration (parts[3])
            return parts.length == 4 ? new FixedDurationTask(description, Integer.parseInt(parts[3])) : null;
        default:
            return null;
        }
    }

    /**
     * Saves the given tasks to the file specified by the file path.
     * @param tasks The ArrayList of tasks to save to the file.
     * @throws AlexException If there is an error saving tasks to the file.
     */
    public void save(ArrayList<Task> tasks) throws AlexException {
        File file = new File(filePath);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                String line = formatTaskForSaving(task);
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new AlexException("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Formats a task as a line for saving to the file.
     * @param task The task to format.
     * @return The formatted string.
     */
    private String formatTaskForSaving(Task task) {
        StringBuilder line = new StringBuilder(task.getTaskType() + " | " + (task.isDone ? "1" : "0") + " | " + task.getDescription());

        if (task instanceof Deadline) {
            line.append(" | ").append(((Deadline) task).by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
        } else if (task instanceof Event) {
            line.append(" | ").append(((Event) task).getStarttime()).append(" | ").append(((Event) task).getEndtime());
        } else if (task instanceof FixedDurationTask) {
            line.append(" | ").append(((FixedDurationTask) task).getDurationInMinutes());
        }

        return line.toString();
    }

    /**
     * Creates the data directory if it does not exist.
     */
    private void createDataDirectory() {
        File dataDir = new File("./data");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }
    }
}