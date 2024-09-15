package Jard;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * The Storage class handles the reading and writing of task data to and from a file.
 * It is responsible for loading tasks from a file upon startup and saving tasks to a file when changes are made.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The file path where task data will be stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the specified file. If the file does not exist, it will be created.
     *
     * @return A list of tasks loaded from the file.
     */
    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String taskData = scanner.nextLine();
                    tasks.add(parseTask(taskData));
                }
                scanner.close();
            } else {
                createFile();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Jartaloon! " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the list of tasks to the specified file.
     *
     * @param tasks The list of tasks to be saved.
     */
    public void saveTasks(List<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks) {
                writer.write(task.toFileString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Jartaloon! " + e.getMessage());
        }
    }

    /**
     * Parses a string representing a task from the file and converts it into a Task object.
     *
     * @param taskData The string representation of the task.
     * @return The corresponding Task object.
     * @throws JardException If the task type is unrecognized or the task data is invalid.
     */
    private Task parseTask(String taskData) throws JardException {
        String[] parts = taskData.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task;
        switch (type) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                String by = parts[3];
                task = new Deadline(description, by);
                break;
            case "E":
                String from = parts[3];
                String to = parts[4];
                task = new Event(description, from, to);
                break;
            default:
                throw new JardException("Jartaloon! " + taskData);
        }
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Creates the file and its parent directories if they do not exist.
     */
    private void createFile() {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Jartaloon! " + e.getMessage());
        }
    }
}
