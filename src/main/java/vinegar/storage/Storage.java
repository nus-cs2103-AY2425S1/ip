package vinegar.storage;

import vinegar.task.Deadline;
import vinegar.task.Event;
import vinegar.task.Task;
import vinegar.task.Todo;
import vinegar.Validator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles reading from and writing to the storage file for tasks.
 * <p>
 * The Storage class is responsible for ensuring that the storage file exists,
 * loading tasks from the file into memory, and saving tasks back to the file.
 */
public class Storage {

    private static final String FILE_PATH = "data/vinegar.txt";

    /**
     * Loads tasks from the storage file.
     *
     * @return A list of tasks loaded from the file.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public List<Task> load() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            loadSampleData(tasks);
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTaskLine(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            throw new IOException("OOPS!!! Unable to load tasks from file: " + e.getMessage(), e); // More descriptive error
        }

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
        assert tasks != null : "Tasks list should not be null when saving.";

        File file = new File(FILE_PATH);
        File directory = file.getParentFile();

        if (directory != null && !directory.exists()) {
            directory.mkdirs(); // Ensure parent directories exist
        }

        if (directory != null) {
            assert directory.exists() : "Directory should exist after trying to create it.";
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                assert task != null : "Task should not be null when writing to file.";
                writer.write(task.toFileFormat() + "\n");
            }
        } catch (IOException e) {
            throw new IOException("Failed to save tasks to file: " + e.getMessage(), e); // Better error message
        }
    }

    // Other methods remain the same


    /**
     * Loads sample tasks into the list of tasks.
     *
     * @param tasks The empty list of tasks.
     */
    private void loadSampleData(List<Task> tasks) {
        tasks.add(new Todo("Read documentation"));
        tasks.add(new Deadline("Submit project proposal", "2023-09-25 11:59"));
        tasks.add(new Event("Team meeting", "2023-09-22 02:00", "2023-09-23 04:00"));
    }

    /**
     * Parses a line from the storage file and creates the corresponding task.
     *
     * @param line The line to parse.
     * @return The created task, or null if parsing fails.
     */
    private Task parseTaskLine(String line) {
        String[] parts = line.split(" \\| ");
        Validator.validateTaskLineFormat(parts, 3); // Ensure there are at least 3 parts

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Validator.validateTaskType(type);

        Task task = createTask(type, parts);
        if (task != null && isDone) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Creates a task object based on its type and parts.
     *
     * @param type  The type of the task (Todo, Deadline, Event).
     * @param parts The parts of the task line (split data).
     * @return The created task, or null if the type is unknown.
     */
    private Task createTask(String type, String[] parts) {
        switch (type) {
        case "T":
            return new Todo(parts[2]);
        case "D":
            Validator.validateTaskLineFormat(parts, 4); // Ensure deadline task has at least 4 parts
            return new Deadline(parts[2], parts[3]);
        case "E":
            Validator.validateTaskLineFormat(parts, 5); // Ensure event task has at least 5 parts
            return new Event(parts[2], parts[3], parts[4]);
        default:
            return null;
        }
    }

}
