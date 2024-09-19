package Buu;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The Storage class is responsible for handling the loading and saving of tasks
 * to and from a file in the GPT application. It manages the file I/O operations
 * and ensures that tasks are correctly persisted.
 */
public class Storage {
    private Path filePath;

    /**
     * Constructs a Storage object with the specified file path for storing tasks.
     * If the directory for the file does not exist, it will be created.
     *
     * @param filePath The file path where tasks will be stored.
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
        try {
            if (!Files.exists(this.filePath.getParent())) {
                Files.createDirectories(this.filePath.getParent());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating directories: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the specified file and returns them as an ArrayList of Task objects.
     * If the file does not exist or an error occurs during reading, an empty list is returned.
     *
     * @return An ArrayList of Task objects loaded from the file.
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        if (Files.exists(filePath)) {
            try (BufferedReader reader = Files.newBufferedReader(filePath)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Task task = parseTaskFromFile(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                }
            } catch (IOException e) {
                System.out.println("An error occurred while loading tasks: " + e.getMessage());
            }
        }
        return tasks;
    }

    /**
     * Parses a line from the file and converts it to a Task object.
     *
     * @param line The line to be parsed.
     * @return The corresponding Task object, or null if parsing fails.
     */
    private Task parseTaskFromFile(String line) {
        String[] parts = line.split("\\|");
        if (parts.length < 4) {
            System.out.println("Invalid task format: " + line);
            return null;
        }

        String taskType = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();
        int priority = Integer.parseInt(parts[parts.length - 1].trim());
        Task task = null;

        switch (taskType) {
        case "T":
            task = new ToDo(description);
            break;
        case "D":
            LocalDateTime byDateTime = Parser.parseDateTime(parts[3].trim());
            task = new Deadline(description, byDateTime);
            break;
        case "E":
            LocalDateTime startDateTime = Parser.parseDateTime(parts[3].trim());
            LocalDateTime endDateTime = Parser.parseDateTime(parts[4].trim());
            task = new Event(description, startDateTime, endDateTime);
            break;
        default:
            System.out.println("Unknown task type: " + taskType);
            return null;
        }

        if (task != null) {
            task.setPriority(priority);
        }

        return task;
    }


    /**
     * Saves the given list of tasks to the specified file. Each task is written in
     * a format suitable for loading later.
     *
     * @param tasks The list of Task objects to be saved to the file.
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks: " + e.getMessage());
        }
    }
}
