package yapper.storage;

import java.io.*;
import java.util.ArrayList;

import yapper.exception.YapperException;
import yapper.task.Deadline;
import yapper.task.Event;
import yapper.task.Task;
import yapper.task.Todo;

/**
 * Handles the loading and saving of tasks to and from the specified file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object to handle reading from and writing to the file at the specified path.
     *
     * @param filePath The path of the file where tasks will be saved and loaded from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the tasks from the file.
     *
     * @return A list of tasks loaded from the file.
     * @throws IOException If there is an error reading from the file.
     */
    public ArrayList<Task> load() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        return readTasksFromFile(file);
    }

    /**
     * Reads tasks from the file.
     *
     * @param file The file to read the tasks from.
     * @return A list of tasks parsed from the file.
     * @throws IOException If there is an error reading from the file.
     */
    private ArrayList<Task> readTasksFromFile(File file) throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Task task = parseTask(line);
                    tasks.add(task);
                } catch (YapperException e) {
                    System.out.println("Error loading task: " + e.getMessage());
                }
            }
        }

        return tasks;
    }

    /**
     * Parses a task from a string.
     *
     * @param line The line to parse.
     * @return The Task object parsed from the line.
     * @throws YapperException If the task cannot be parsed.
     */
    private Task parseTask(String line) throws YapperException {
        String[] parts = line.split(" \\| ");

        Task task = createTaskFromParts(parts);
        if (task != null && parts[1].equals("1")) {
            task.markAsDone();
        }

        return task;
    }

    /**
     * Creates a Task object from the split parts of a line.
     *
     * @param parts The parts of the task line.
     * @return The corresponding Task object.
     * @throws YapperException If the task type is invalid or parts are missing.
     */
    private Task createTaskFromParts(String[] parts) throws YapperException {
        switch (parts[0]) {
            case "T":
                return new Todo(parts[2]);
            case "D":
                return new Deadline(parts[2], parts[3]);
            case "E":
                return new Event(parts[2], parts[3], parts[4]);
            default:
                throw new YapperException("Unknown task type: " + parts[0]);
        }
    }

    /**
     * Saves the tasks to the file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws IOException If there is an error writing to the file.
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        File file = new File(filePath);
        file.getParentFile().mkdirs();

        writeTasksToFile(file, tasks);
    }

    /**
     * Writes tasks to the file.
     *
     * @param file  The file to write tasks to.
     * @param tasks The list of tasks to be written.
     * @throws IOException If there is an error writing to the file.
     */
    private void writeTasksToFile(File file, ArrayList<Task> tasks) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                writer.write(task.toSaveFormat());
                writer.newLine();
            }
        }
    }
}
