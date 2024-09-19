package fanny.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fanny.task.Deadline;
import fanny.task.Event;
import fanny.task.Task;
import fanny.task.ToDo;


/**
 * Handles the loading and saving of tasks to a file.
 */
public class Storage {

    /** The file path where the tasks are stored. */
    private String filePath;

    /**
     * Constructs a {@code Storage} object with the specified file path.
     *
     * @param filePath The file path where tasks will be loaded from and saved to.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file at the specified file path.
     *
     * @return A list of tasks loaded from the file.
     * @throws IOException If the file does not exist.
     */
    public List<Task> loadTask() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        createNewFile(file);

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Task task = parseTask(line);
            assert task != null : "Loaded task should not be null";
            tasks.add(task);
        }

        return tasks;
    }

    /**
     * Creates the file and its parent directories, if it does not exist.
     * Handles any {@code IOException} that occurs during file creation.
     *
     * @param file The file to check and create if necessary.
     * @throws IOException If an error occurs while creating the file or directories.
     */
    private void createNewFile(File file) throws IOException {
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                System.out.println("Created new file: " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            throw new IOException("Error creating file: " + file.getAbsolutePath(), e);
        }
    }

    /**
     * Saves the list of tasks to the file at the specified file path.
     *
     * @param tasks The list of tasks to be saved.
     * @throws IOException If an I/O error occurs during saving.
     */
    public void save(List<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for (Task task : tasks) {
            writer.write(taskToString(task) + "\n");
        }
        writer.close();

        List<Task> loadedTasks = loadTask();
        assert tasks.size() == loadedTasks.size() : "Number of tasks saved and loaded should be equal";
    }

    /**
     * Parses a task from a line of text in the file.
     *
     * @param line The line of text representing a task.
     * @return The task parsed from the line.
     * @throws IllegalArgumentException If the task type in the line is unknown.
     */
    private Task parseTask(String line) throws IllegalArgumentException {
        String[] parts = line.split("\\|");
        String taskType = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        Task task;

        switch (taskType) {
        case "T":
            task = new ToDo(description);
            break;
        case "D":
            task = new Deadline(description, parts[3].trim());
            break;
        case "E":
            task = new Event(description, parts[3].trim(), parts[4].trim());
            break;
        default:
            throw new IllegalArgumentException("Unknown task type: " + taskType);
        }

        if (isDone) {
            task.markAsDone();
        }

        return task;
    }



    /**
     * Converts a task to a string suitable for saving to a file.
     *
     * @param task The task to be converted.
     * @return A string representation of the task.
     */
    private String taskToString(Task task) {
        String taskType = getTaskType(task);
        String isDone = task.isDone() ? "1" : "0";
        String description = task.getDescription();
        String extra = getTaskExtraInfo(task);

        return taskType + " | " + isDone + " | " + description + extra;
    }

    /**
     * Determines the task type as a string.
     *
     * @param task The task to evaluate.
     * @return "T" for ToDo, "D" for Deadline, or "E" for Event.
     */
    private String getTaskType(Task task) {
        if (task instanceof ToDo) {
            return "T";
        } else if (task instanceof Deadline) {
            return "D";
        } else if (task instanceof Event) {
            return "E";
        }
        throw new IllegalArgumentException("Unknown task type");
    }

    /**
     * Gets extra information for a task (e.g., deadline or event time).
     *
     * @param task The task to get extra information from.
     * @return A string containing extra task information, if any.
     */
    private String getTaskExtraInfo(Task task) {
        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return "|" + deadline.getDeadline();
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return "|" + event.getStartTime() + "|" + event.getEndTime();
        }
        return "";
    }

}
