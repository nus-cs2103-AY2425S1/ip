package beechat.util;

import beechat.task.DeadlineTask;
import beechat.task.EventTask;
import beechat.task.Task;
import beechat.task.TodoTask;

import java.io.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles the loading and saving of tasks to and from a file.
 * Provides methods to read and write tasks from and to a file.
 */
public class Storage {

    /** The path to the file where tasks are stored. */
    private final String filePath;

    /** The date and time formatter used to parse and format task dates. */
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the specified file if it exists.
     * Otherwise, it creates a new file and directory if necessary.
     *
     * @return A list of tasks loaded from the file.
     * @throws IOException If an error occurs while reading from or creating the file.
     */
    public List<Task> loadTasks() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        assert file.exists() : "Task file should exist when loading tasks";
        assert file.canRead() : "Task file should be readable";

        try {
            if (!file.exists()) {
                new File(file.getParent()).mkdirs();
                file.createNewFile();
            } else {
                BufferedReader reader = new BufferedReader(new FileReader(filePath));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(" \\| ");
                    String taskType = parts[0];
                    boolean isDone = parts[1].equals("1");
                    String description = parts[2];

                    switch (taskType) {
                        case "T":
                            Task todo = new TodoTask(description);
                            if (isDone) {
                                todo.mark();
                            }
                            tasks.add(todo);
                            break;
                        case "D":
                            try {
                                LocalDateTime by = LocalDateTime.parse(parts[3], FORMATTER);
                                Task deadline = new DeadlineTask(description, by);
                                if (isDone) deadline.mark();
                                tasks.add(deadline);
                            } catch (DateTimeParseException e) {
                                System.out.println("Error when parsing date for deadline task: " + e.getMessage());
                            }
                            break;
                        case "E":
                            try {
                                LocalDateTime from = LocalDateTime.parse(parts[3], FORMATTER);
                                LocalDateTime to = LocalDateTime.parse(parts[4], FORMATTER);
                                Task event = new EventTask(description, from, to);
                                if (isDone) event.mark();
                                tasks.add(event);
                            } catch (DateTimeParseException e) {
                                System.out.println("Error when parsing date for event task: " + e.getMessage());
                            }
                            break;
                        default:
                            throw new IOException("Invalid task type in file: " + taskType);
                    }
                }
                reader.close();
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves tasks to the file.
     *
     * @param tasks The list of tasks to be saved to the file.
     * @throws IOException If an error occurs while writing to the file.
     */
    public void saveTasks(List<Task> tasks) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.write(task.toSaveFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
