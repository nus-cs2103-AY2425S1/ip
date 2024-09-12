package kobe.util;

import kobe.task.Deadline;
import kobe.task.Event;
import kobe.task.Task;
import kobe.task.Todo;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the loading and saving of tasks to and from a file for the Duke chatbot application.
 * The Storage class provides methods to read tasks from a file and write tasks back to a file.
 */
public class Storage {
    /** The path to the file where tasks are stored. */
    private final String filePath;

    /** The date and time formatter used to parse and format task dates. */
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the specified file.
     * If the file does not exist, it creates a new file and directory if necessary.
     *
     * @return A list of tasks loaded from the file.
     * @throws IOException If an error occurs while reading from or creating the file.
     */
    public List<Task> load() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        assert file.exists() : "Task file should exist when loading tasks";
        assert file.canRead() : "Task file should be readable";

        if (!file.exists()) {
            new File(file.getParent()).mkdirs(); // Create the directory if it doesn't exist
            file.createNewFile(); // Create the file if it doesn't exist
        } else {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String taskType = parts[0];
                boolean isDone = parts[1].equals("1");
                String name = parts[2];

                switch (taskType) {
                    case "T":
                        Task todo = new Todo(name);
                        if (isDone) todo.markAsDone();
                        tasks.add(todo);
                        break;
                    case "D":
                        LocalDateTime by = LocalDateTime.parse(parts[3], FORMATTER);
                        Task deadline = new Deadline(name, by);
                        if (isDone) deadline.markAsDone();
                        tasks.add(deadline);
                        break;
                    case "E":
                        LocalDateTime from = LocalDateTime.parse(parts[3], FORMATTER);
                        LocalDateTime to = LocalDateTime.parse(parts[4], FORMATTER);
                        Task event = new Event(name, from, to);
                        if (isDone) event.markAsDone();
                        tasks.add(event);
                        break;
                    default:
                        throw new IOException("Invalid task type in file: " + taskType);
                }
            }
            reader.close();
        }

        return tasks;
    }

    /**
     * Saves the list of tasks to the specified file.
     *
     * @param tasks The list of tasks to be saved to the file.
     * @throws IOException If an error occurs while writing to the file.
     */
    public void save(List<Task> tasks) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (Task task : tasks) {
            writer.write(task.toFileFormat());
            writer.newLine();
        }
        writer.close();
    }
}
