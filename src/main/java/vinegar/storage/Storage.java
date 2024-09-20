package vinegar.storage;

import vinegar.task.Deadline;
import vinegar.task.Event;
import vinegar.task.Task;
import vinegar.task.Todo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles reading from and writing to the storage file for tasks.
 * <p>
 * The Storage class is responsible for ensuring that the storage file exists,
 * loading tasks from the file into memory, and saving tasks back to the file.
 */
public class Storage {
    private String filePath;
    private String directoryPath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        // Get the directory from the file path
        this.directoryPath = new File(filePath).getParent();

        // Assert that the directory path is not null
        assert this.directoryPath != null : "Directory path should not be null.";
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return A list of tasks loaded from the file.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public List<Task> load() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        // If the file doesn't exist, return an empty list
        if (!file.exists()) return tasks;

        // Assert that the file exists before reading
        assert file.exists() : "Task file should exist before attempting to load tasks.";

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];
                Task task = null;

                // Assert that the line format is valid
                assert parts.length >= 3 : "Each task line should have at least 3 parts (type, isDone, description).";

                switch (type) {
                case "T":
                    task = new Todo(description);
                    break;
                case "D":
                    // Assert that the deadline has the required date
                    assert parts.length >= 4 : "Deadline task must have a date.";
                    task = new Deadline(description, parts[3]);
                    break;
                case "E":
                    // Assert that the event has the required start and end times
                    assert parts.length >= 5 : "Event task must have a start and end time.";
                    task = new Event(description, parts[3], parts[4]);
                    break;
                default:
                    assert false : "Unknown task type found in file: " + type;
                }
                if (task != null && isDone) {
                    task.markAsDone();
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new IOException(" OOPS!!! Unable to load tasks from file.");
        }

        // Assert that tasks were successfully loaded
        assert tasks != null : "Tasks list should not be null after loading from file.";
        return tasks;
    }

    /**
     * Saves the given list of tasks to the storage file.
     *
     * @param tasks The list of tasks to save.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void save(List<Task> tasks) throws IOException {
        // Assert that tasks list is not null
        assert tasks != null : "Tasks list should not be null when saving.";

        // Ensure that the directory exists before saving the file
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs(); // Create the directory if it doesn't exist
        }

        // Assert that the directory exists after attempting to create it
        assert directory.exists() : "Directory should exist after trying to create it.";

        // Now write to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                // Assert that task format is valid before writing to file
                assert task != null : "Task should not be null when writing to file.";
                writer.write(task.toFileFormat() + "\n");
            }
        }
    }
}
