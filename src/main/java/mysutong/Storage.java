package mysutong;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles file-based storage operations for the MySutong application, including loading and saving tasks.
 * Now supports saving and loading task priority.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a new Storage instance with the specified file path.
     *
     * @param filePath the path to the file where tasks are stored and retrieved.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified by {@code filePath}.
     * Creates the file and its directories if they do not already exist.
     * Parses the file to reconstruct task objects based on their saved format.
     *
     * @return a list of {@link Task} objects loaded from the file.
     * @throws IOException if an I/O error occurs when opening or creating the file.
     */
    public List<Task> load() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }
            file.createNewFile();
            return new ArrayList<>(); // Return an empty list if file is newly created.
        }

        Scanner scanner = new Scanner(file);
        ArrayList<Task> tasks = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String[] parts = scanner.nextLine().split(" \\| ");
            String taskType = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];
            int priority = Integer.parseInt(parts[parts.length - 1]); // Priority is the last part

            Task task = parseTask(parts, taskType, description, isDone, priority);
            tasks.add(task);
        }
        scanner.close();
        return tasks;
    }

    /**
     * Saves the current state of tasks to the file specified by {@code filePath}.
     * Ensures that the directory structure for the file exists, creating it if necessary.
     *
     * @param tasks the {@link TaskList} containing tasks to be saved.
     * @throws IOException if an I/O error occurs when writing to the file.
     */
    public void save(TaskList tasks) throws IOException {
        File file = new File(filePath);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (Task task : tasks.getTasks()) {
            writer.write(task.toFileString());
            writer.newLine();
        }
        writer.close();
    }

    /**
     * Helper method to parse task data from loaded strings and create task objects.
     * Now includes priority when creating task objects.
     *
     * @param parts the array of string parts from a split line of task data.
     * @param taskType the type of the task ('T', 'D', 'E').
     * @param description the description of the task.
     * @param isDone the completion status of the task.
     * @param priority the priority of the task (1 for high, 2 for medium, 3 for low).
     * @return the constructed Task object.
     * @throws IllegalStateException if the task type is unknown.
     */
    private Task parseTask(String[] parts, String taskType, String description, boolean isDone, int priority) {
        Task task;
        switch (taskType) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            LocalDateTime deadlineDate = LocalDateTime.parse(parts[3], DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            task = new Deadline(description, deadlineDate);
            break;
        case "E":
            LocalDateTime eventStart = LocalDateTime.parse(parts[3], DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            LocalDateTime eventEnd = LocalDateTime.parse(parts[4], DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            task = new Event(description, eventStart, eventEnd);
            break;
        default:
            throw new IllegalStateException("Unknown task type: " + taskType);
        }
        if (isDone) {
            task.markAsDone();
        }
        task.setPriority(priority); // Set the priority from file
        return task;
    }
}
