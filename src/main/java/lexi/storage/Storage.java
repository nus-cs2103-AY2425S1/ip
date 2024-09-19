package lexi.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import lexi.exception.LexiException;
import lexi.task.Deadline;
import lexi.task.Event;
import lexi.task.Task;
import lexi.task.Todo;

/**
 * Handles the loading and saving of tasks to and from a file.
 * This class manages the persistence of task data,
 * including reading tasks from a file and writing tasks back to the file.
 * ChatGPT used to de-clutter deeply nested code to adhere to SLAP principle.
 */
public class Storage {
    private final File dataFile;
    private final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Constructs a Storage object that handles the specified file.
     * Ensures that the file and its directory exist, creating them if necessary.
     *
     * @param filepath The path to the file used for storing tasks.
     * @throws LexiException If the directory or file cannot be created.
     */
    public Storage(String filepath) throws LexiException {
        assert filepath != null && !filepath.isEmpty() : "Filepath cannot be null or empty.";
        this.dataFile = new File(filepath);
        ensureDirectoryAndFileExist();
    }

    /**
     * Ensures that the directory and file exist. If they do not exist, they are created.
     *
     * @throws LexiException If the directory or file cannot be created.
     */
    private void ensureDirectoryAndFileExist() throws LexiException {
        File directory = dataFile.getParentFile();
        if (directory != null && !directory.exists() && !directory.mkdirs()) {
            throw new LexiException("Failed to create directory: " + directory.getAbsolutePath());
        }
        try {
            if (!dataFile.exists() && !dataFile.createNewFile()) {
                throw new LexiException("Failed to create file: " + dataFile.getAbsolutePath());
            }
        } catch (IOException e) {
            throw new LexiException("Failed to create file: " + dataFile.getAbsolutePath());
        }
        assert dataFile.exists() : "Data file should exist after constructor completes.";
    }

    /**
     * Loads tasks from the file and returns them as an ArrayList.
     *
     * @return A list of tasks loaded from the file.
     * @throws LexiException If the file is not found or if there is an error reading from the file.
     */
    public ArrayList<Task> load() throws LexiException {
        assert dataFile.exists() : "Data file should exist before loading tasks.";
        try {
            return loadTasksFromFile();
        } catch (FileNotFoundException e) {
            throw new LexiException("File not found: " + dataFile.getAbsolutePath());
        }
    }

    /**
     * Reads tasks from the file and parses them into a list of tasks.
     *
     * @return The list of tasks read from the file.
     * @throws FileNotFoundException If the file cannot be found.
     */
    private ArrayList<Task> loadTasksFromFile() throws FileNotFoundException, LexiException {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(dataFile);
        while (scanner.hasNextLine()) {
            String[] parts = scanner.nextLine().split(" !- ");
            tasks.add(parseTask(parts));
        }
        scanner.close();
        return tasks;
    }

    /**
     * Parses a task from an array of parts.
     *
     * @param parts The parts of the task stored in the file.
     * @return The parsed task object.
     * @throws LexiException If an unknown task type is encountered.
     */
    private Task parseTask(String[] parts) throws LexiException {
        String taskType = parts[0];
        String taskName = parts[2];
        boolean isDone = parts[1].equals("1");

        switch (taskType) {
        case "T":
            return parseTodoTask(taskName, isDone);
        case "D":
            return parseDeadlineTask(parts, taskName, isDone);
        case "E":
            return parseEventTask(parts, taskName, isDone);
        default:
            throw new LexiException("Unknown task type: " + taskType);
        }
    }

    /**
     * Parses a Todo task.
     *
     * @param taskName The name of the task.
     * @param isDone Whether the task is marked as done.
     * @return The parsed Todo task.
     */
    private Todo parseTodoTask(String taskName, boolean isDone) {
        Todo todoTask = new Todo(taskName);
        if (isDone) {
            todoTask.setDone(true);
        }
        return todoTask;
    }

    /**
     * Parses a Deadline task.
     *
     * @param parts The parts of the task.
     * @param taskName The name of the task.
     * @param isDone Whether the task is marked as done.
     * @return The parsed Deadline task.
     */
    private Deadline parseDeadlineTask(String[] parts, String taskName, boolean isDone) {
        String deadline = parts[3];
        LocalDateTime by = LocalDateTime.parse(deadline, this.inputFormatter);
        Deadline deadlineTask = new Deadline(taskName, by);
        if (isDone) {
            deadlineTask.setDone(true);
        }
        return deadlineTask;
    }

    /**
     * Parses an Event task.
     *
     * @param parts The parts of the task.
     * @param taskName The name of the task.
     * @param isDone Whether the task is marked as done.
     * @return The parsed Event task.
     */
    private Event parseEventTask(String[] parts, String taskName, boolean isDone) {
        LocalDateTime from = LocalDateTime.parse(parts[3], this.inputFormatter);
        LocalDateTime to = LocalDateTime.parse(parts[4], this.inputFormatter);
        Event eventTask = new Event(taskName, from, to);
        if (isDone) {
            eventTask.setDone(true);
        }
        return eventTask;
    }

    /**
     * Updates the file with the current list of tasks.
     * Overwrites the file with the current task list.
     *
     * @param tasks The list of tasks to be saved to the file.
     * @throws LexiException If there is an error writing to the file.
     */
    public void updateStorage(ArrayList<Task> tasks) throws LexiException {
        assert tasks != null : "Tasks list cannot be null.";
        this.tasks = tasks;
        try {
            writeTasksToFile(tasks);
            validateFileNotEmpty();
        } catch (IOException e) {
            throw new LexiException("Something went wrong with writing to data\n"
                    + "Please contact your system administrator!\n"
                    + "Error Message:\n" + e.getMessage());
        }
    }

    /**
     * Writes the list of tasks to the file.
     *
     * @param tasks The list of tasks to be written.
     * @throws IOException If an I/O error occurs.
     */
    private void writeTasksToFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(dataFile);
        for (Task task : tasks) {
            fw.write(formatTaskForStorage(task) + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Formats a task into a string for storage.
     *
     * @param task The task to be formatted.
     * @return The formatted task string.
     */
    private String formatTaskForStorage(Task task) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        char taskType = task.toString().split(" ")[0].charAt(1);

        switch (taskType) {
        case 'T':
            return String.format("%c !- %d !- %s", taskType,
                    task.getIsDone() ? 1 : 0, task.getTaskName());
        case 'D':
            Deadline deadline = (Deadline) task;
            return String.format("%c !- %d !- %s !- %s", taskType,
                    deadline.getIsDone() ? 1 : 0, deadline.getTaskName(),
                    deadline.getBy().format(formatter));
        case 'E':
            Event event = (Event) task;
            return String.format("%c !- %d !- %s !- %s !- %s", taskType,
                    event.getIsDone() ? 1 : 0, event.getTaskName(),
                    event.getFrom().format(formatter), event.getTo().format(formatter));
        default:
            throw new IllegalArgumentException("Unknown task type: " + taskType);
        }
    }

    /**
     * Validates that the file is not empty after saving.
     *
     * @throws LexiException If the file is empty.
     */
    private void validateFileNotEmpty() throws LexiException {
        if (dataFile.length() == 0) {
            throw new LexiException("File should not be empty after updating storage.");
        }
    }
}
