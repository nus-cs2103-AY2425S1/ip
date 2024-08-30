package puke.storage;

import puke.tasks.Deadline;
import puke.tasks.Event;
import puke.tasks.Task;
import puke.tasks.Todo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Handles loading and saving tasks to and from a file.
 */
public class Storage {
    private static final String FILE_PATH = "./data/tasks.txt";

    /**
     * Loads tasks from the file and returns them as an ArrayList.
     *
     * @return an ArrayList of tasks loaded from the file
     */
    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        Path path = Paths.get(FILE_PATH);
        if (Files.exists(path)) {
            try (BufferedReader reader = Files.newBufferedReader(path)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    try {
                        tasks.add(parseTask(line));
                    } catch (Exception e) {
                        System.out.println("Skipping corrupt task: " + line);
                    }
                }
            } catch (IOException e) {
                System.out.println("Failed to read tasks from file: " + e.getMessage());
            }
        }
        return tasks;
    }

    /**
     * Saves the list of tasks to the file.
     *
     * @param tasks the ArrayList of tasks to be saved
     */
    public static void saveTasks(ArrayList<Task> tasks) {
        Path path = Paths.get(FILE_PATH);
        try {
            Files.createDirectories(path.getParent()); // Ensure directory exists
            try (BufferedWriter writer = Files.newBufferedWriter(path)) {
                for (Task task : tasks) {
                    writer.write(task.toFileFormat());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to save tasks: " + e.getMessage());
        }
    }

    /**
     * Parses a task from its string representation in the file format.
     *
     * @param line the string representation of a task
     * @return the Task object parsed from the string
     * @throws IllegalArgumentException if the task type is unsupported or the format is invalid
     */
    private static Task parseTask(String line) throws IllegalArgumentException {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        switch (type) {
            case "T":
                return new Todo(description, isDone);
            case "D":
                return new Deadline(description, isDone, parts.length > 3 ? parts[3] : null);
            case "E":
                return new Event(description, isDone, parts.length > 3 ? parts[3] : null, parts.length > 4 ? parts[4] : null);
            default:
                throw new IllegalArgumentException("Unsupported task type: " + type);
        }
    }
}
