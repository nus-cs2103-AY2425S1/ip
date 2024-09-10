package assistinator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * API of the storage class
 */
public class Storage {
    private String filePath;

    /**
     * Initialise a storage class
     * @param filePath File path to tasks
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves tasks to file in initialised file path
     * @param tasks Task list
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks) {
                writer.write(task.toFileString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads task list from file
     * @return task list
     * @throws AssitinatorExceptions If file not in provided file path
     */
    public ArrayList<Task> loadTasks() throws AssitinatorExceptions {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            File file = new File(filePath);
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String[] parts = s.nextLine().split("\\|");
                String type = parts[0].trim();
                Task task = getTask(parts, type);
                tasks.add(task);
            }
            return tasks;
        } catch (FileNotFoundException e) {
            throw new AssitinatorExceptions("File not found");
        }
    }

    /**
     * Converts lines in files to tasks
     * @param parts Task information
     * @param type Type of task
     * @return Task
     */
    public Task getTask(String[] parts, String type) {
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        Task task;
        switch (type) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            task = new Deadline(description, parts[3].trim());
            break;
        case "E":
            task = new Event(
                    description,
                    parts[3].trim(),
                    parts[4].substring(parts[4].indexOf(' ') + 1)
            );
            break;
        default:
            throw new IllegalArgumentException("Unknown task type: " + type);
        }
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }
}
