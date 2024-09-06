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

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];
                Task task = null;

                switch (type) {
                    case "T":
                        task = new Todo(description);
                        break;
                    case "D":
                        task = new Deadline(description, parts[3]);
                        break;
                    case "E":
                        task = new Event(description, parts[3], parts[4]);
                        break;
                }
                if (task != null && isDone) {
                    task.markAsDone();
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new IOException(" OOPS!!! Unable to load tasks from file.");
        }
        return tasks;
    }

    /**
     * Saves the given list of tasks to the storage file.
     *
     * @param tasks The list of tasks to save.
     * @throws IOException If an I/O error occurs while writing to the file.
     */

    public void save(List<Task> tasks) throws IOException {
        // Ensure that the directory exists before saving the file
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs(); // Create the directory if it doesn't exist
        }

        // Now write to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat() + "\n");
            }
        }
    }
}
