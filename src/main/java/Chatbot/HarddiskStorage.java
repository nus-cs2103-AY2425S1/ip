package Chatbot;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDateTime;

/**
 * The {@code HarddiskStorage} class handles the loading and saving of tasks to and from the hard disk.
 * It supports various types of tasks, including ToDo, Deadline, and Event tasks.
 */
public class HarddiskStorage {
    private final String filePath;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    /**
     * Constructs a {@code HarddiskStorage} object with the specified file path.
     *
     * @param filePath the path of the file where tasks are stored.
     */
    public HarddiskStorage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified by the {@code filePath}.
     * If the file does not exist or cannot be read, an empty task list is returned.
     *
     * @return a map of tasks, where the key is the task ID and the value is the task object.
     */
    public Map<Integer, Task> load() {
        Map<Integer, Task> tasks = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                int taskId = Integer.parseInt(parts[0]);
                String type = parts[1];
                boolean isDone = parts[2].equals("1");
                String description = parts[3];
                Task task = null;

                if (type.equals("T")) {
                    task = new ToDo(description);
                } else if (type.equals("D")) {
                    LocalDateTime by = LocalDateTime.parse(parts[4], formatter);
                    task = new Deadline(description, by);
                } else if (type.equals("E")) {
                    LocalDateTime from = LocalDateTime.parse(parts[4], formatter);
                    LocalDateTime to = LocalDateTime.parse(parts[5], formatter);
                    task = new Event(description, from, to);
                }

                if (task != null && isDone) {
                    task.markDone();
                }

                tasks.put(taskId, task);
            }
        } catch (IOException e) {
            System.out.println("    No existing task file found. Starting with an empty task list.");
        }
        return tasks;
    }

    /**
     * Saves the given map of tasks to the file specified by the {@code filePath}.
     * If the file or its directories do not exist, they will be created.
     * If an error occurs during saving, an error message will be printed.
     *
     * @param tasks a map of tasks to be saved, where the key is the task ID and the value is the task object.
     */
    public void save(Map<Integer, Task> tasks) {
        try {
            File file = new File(filePath);
            File parentDir = file.getParentFile();

            // Ensure parent directories exist
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs(); // Create the directories if they don't exist
            }

            // Create the file if it doesn't exist
            if (!file.exists()) {
                file.createNewFile(); // This will create the file if it doesn't exist
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (Map.Entry<Integer, Task> entry : tasks.entrySet()) {
                    Task task = entry.getValue();
                    writer.write(entry.getKey() + " | " + task.toFileFormat());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("    Error saving tasks to file.");
        }
    }
}
