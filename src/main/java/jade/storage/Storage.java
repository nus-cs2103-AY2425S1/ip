package jade.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import jade.task.Deadline;
import jade.task.Event;
import jade.task.Task;
import jade.task.Todo;

/**
 * Manages the loading and saving of tasks to and from a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path of the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified in the file path.
     * If the file or its parent directories do not exist, they will be created.
     *
     * @return An ArrayList of tasks loaded from the file.
     *     Returns an empty list if the file does not exist or cannot be read.
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        File parentDir = file.getParentFile();

        if (!parentDir.exists()) {
            parentDir.mkdirs(); // Create parent directories if they do not exist
        } else if (!file.exists()) {
            try {
                file.createNewFile(); // Create a new file if it does not exist
            } catch (IOException e) {
                System.out.println("Error creating a file: " + e.getMessage());
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(parseTask(line));
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Saves the specified tasks to the file.
     * Overwrites the file if it already exists.
     *
     * @param tasks An ArrayList of tasks to be saved to the file.
     *     Each task will be converted to a data string format and written to the file.
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.write(task.toDataString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    /**
     * Parses a line of task data from the file into a Task object.
     *
     * @param data The line of task data read from the file.
     * @return A Task object parsed from the given data string.
     *     Returns null if the data format is invalid or unrecognised.
     */
    private Task parseTask(String data) {
        String[] parts = data.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (taskType) {
        case "T":
            return new Todo(description, isDone);
        case "D":
            String by = parts[3];
            return new Deadline(description, by, isDone);
        case "E":
            String[] timeParts = parts[3].split(" - ");
            String from = timeParts[0];
            String to = timeParts[1];
            return new Event(description, from, to, isDone);
        default:
            return null;
        }
    }
}
