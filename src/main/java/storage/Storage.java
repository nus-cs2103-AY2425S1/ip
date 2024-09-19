package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

/**
 * Storage class is a class that deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    private static final String FILE_PATH = "./data/OrangeCat.txt";

    /**
     * Saves tasks to the OrangeCat.txt
     * @param tasks the list of tasks that contains tasks to be saved
     */
    public static void saveTasks(List<Task> tasks) {
        try {
            Path path = Paths.get(FILE_PATH);
            Files.createDirectories(path.getParent()); // Create directories if they don't exist
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
            for (Task task : tasks) {
                writer.write(task.toDataString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the OrangeCat.txt
     * @param tasks the list of tasks that contains tasks to be loaded
     */
    public static void loadTasks(List<Task> tasks) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            String line; // To hold each line read from the file
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String taskType = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];
                loadTaskHelper(tasks, taskType, parts, description, isDone);
            }
            reader.close();
        } catch (FileNotFoundException e) { // If FILE_PATH does not exist
            System.out.println("Data file not found, starting with an empty task list.");
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("The data file is corrupted, starting with an empty task list.");
            tasks.clear();
        }
    }

    /**
     * Helper method to load tasks from the file
     * @param items
     * @param taskType
     * @param parts
     * @param description
     * @param isDone
     * @return
     */
    private static boolean loadTaskHelper(List<Task> items, String taskType, String[] parts, String description,
                                          boolean isDone) {
        Task task;
        switch (taskType) {
        case "T":
            int priorityForToDo = Integer.valueOf(parts[3]);
            task = new ToDo(description, priorityForToDo);
            break;
        case "D":
            String by = parts[3];
            int priorityForDeadline = Integer.valueOf(parts[4]);
            task = new Deadline(description, LocalDate.parse(by), priorityForDeadline);
            break;
        case "E":
            String from = parts[3];
            String to = parts[4];
            int priorityForEvent = Integer.valueOf(parts[5]);
            task = new Event(description, LocalDate.parse(from), LocalDate.parse(to), priorityForEvent);
            break;
        default:
            System.out.println("Unknown task type: " + taskType);
            return true;
        }
        if (task != null) {
            task.setDone(isDone);
            items.add(task);
        }
        return false;
    }
}
