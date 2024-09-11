package lutchat;

import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class is responsible for saving and loading tasks from a file.
 * It handles the persistence of tasks between sessions of the application.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path of the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the specified file and returns them as an ArrayList.
     * If the file does not exist, an empty task list is returned.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(filePath);

        // Ensure the directory exists
        file.getParentFile().mkdirs();

        // Create a new file if it doesn't exist
        if (!file.exists()) {
            file.createNewFile();
            return taskList;
        }

        // Read the file and process each line
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                Task task = parseTask(data);
                if (task != null) {
                    taskList.add(task);
                }
            }
        }
        return taskList;
    }

    /**
     * Saves the current list of tasks to the specified file.
     *
     * @param taskList The TaskList object containing the tasks to be saved.
     */
    public void save(TaskList taskList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : taskList.getTasks()) {
                writer.write(task.toFileFormat() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Oh no! An error occurred while saving tasks...");
        }
    }

    /**
     * Parses a line from the file and converts it into a Task object.
     * If the line is corrupted or incomplete, it returns null.
     *
     * @param data The line from the file representing a task.
     * @return The parsed Task object, or null if the line is corrupted.
     */
    private Task parseTask(String data) {
        String[] parts = data.split(" \\| ");
        if (parts.length < 3) {
            System.out.println("Skipping corrupted data: " + data);
            return null;
        }

        String taskType = parts[0];
        boolean done = parts[1].equals("1");
        String desc = parts[2];

        Task task = createTask(taskType, parts, data);
        if (task != null && done) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Creates a Task object based on the task type and input parts.
     *
     * @param taskType The type of task (T, D, E).
     * @param parts    The data split into parts (type, done status, description, etc.).
     * @param data     The original line of data for error reporting.
     * @return The created Task object, or null if the task type is unknown or the data is corrupted.
     */
    private Task createTask(String taskType, String[] parts, String data) {
        Task task = null;
        switch (taskType) {
            case "T":
                task = new Todo(parts[2]);
                break;
            case "D":
                if (parts.length >= 4) {
                    task = new Deadline(parts[2], parts[3]);
                } else {
                    reportCorruption(data);
                }
                break;
            case "E":
                if (parts.length >= 5) {
                    task = new Event(parts[2], parts[3], parts[4]);
                } else {
                    reportCorruption(data);
                }
                break;
            default:
                System.out.println("Skipping unknown task type: " + taskType);
        }
        return task;
    }

    /**
     * Reports corrupted data lines.
     *
     * @param data The corrupted data line.
     */
    private void reportCorruption(String data) {
        System.out.println("Skipping corrupted line: " + data);
    }
}
