package yapper.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(" \\| ");
                    Task task = null;
                    try {
                        switch (parts[0]) {
                        case "T":
                            task = new Todo(parts[2]);
                            break;
                        case "D":
                            task = new Deadline(parts[2], parts[3]);
                            break;
                        case "E":
                            task = new Event(parts[2], parts[3], parts[4]);
                            break;
                        default:
                            // Add default case to handle unexpected input
                            System.out.println("Unknown task type: " + parts[0]);
                        }
                        if (task != null && parts[1].equals("1")) {
                            task.markAsDone();
                        }
                        tasks.add(task);
                    } catch (YapperException e) {
                        System.out.println("Error loading task from file: " + e.getMessage());
                        // Continue loading other tasks even if this one fails
                    }
                }
            }
        }
        return tasks;
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                writer.write(task.toSaveFormat());
                writer.newLine();
            }
        }
    }
}
