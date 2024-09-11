package bean.storage;

import bean.task.DeadlineTask;
import bean.task.EventTask;
import bean.task.Task;
import bean.task.TodoTask;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles loading and saving tasks to and from a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks will be stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file.
     * If the file does not exist, it is created.
     *
     * @return A list of tasks loaded from the file.
     */
    public List<Task> load() {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs(); // Create directory if it doesn't exist
                file.createNewFile(); // Create file if it doesn't exist
            }

            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" \\| ");
                String taskType = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                switch (taskType) {
                case "T":
                    TodoTask todo = new TodoTask(description);
                    if (isDone) {
                        todo.completeTask();
                    }
                    tasks.add(todo);
                    break;
                case "D":
                    String by = parts[3];
                    DeadlineTask deadline = new DeadlineTask(description, by);
                    if (isDone) {
                        deadline.completeTask();
                    }
                    tasks.add(deadline);
                    break;
                case "E":
                    String from = parts[3];
                    String to = parts[4];
                    EventTask event = new EventTask(description, from, to);
                    if (isDone) {
                        event.completeTask();
                    }
                    tasks.add(event);
                    break;
                }
            }
            fileScanner.close();
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Saves the list of tasks to the file.
     *
     * @param tasks The list of tasks to be saved.
     */
    public void save(List<Task> tasks) {
        assert tasks != null : "Task list should not be null when saving.";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.println(task.toSaveFormat());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}