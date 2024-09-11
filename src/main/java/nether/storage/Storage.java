package nether.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import nether.NetherException;
import nether.task.DeadlineTask;
import nether.task.EventTask;
import nether.task.Task;
import nether.task.TodoTask;


/**
 * Handles the storage of tasks to and from a file.
 * The {@code Storage} class provides functionality to save tasks to a file and load tasks from a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a {@code Storage} object with the specified file path.
     *
     * @param filePath The path to the file where tasks will be saved or loaded from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the specified list of tasks to the data file.
     * Each task is saved in a format defined by {@code Task.toSaveFormat()}.
     *
     * @param tasks The list of tasks to be saved in the data file.
     */
    public void saveTasks(List<Task> tasks) {
        File file = new File(filePath);
        file.getParentFile().mkdirs(); // create the parent directory just in case it doesn't exist yet

        try (FileWriter writer = new FileWriter(file)) {
            for (Task task : tasks) {
                writer.write(task.toSaveFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads the tasks present in the data file.
     * If the file does not exist, an empty list is returned.
     *
     * @return A list of tasks loaded from the data file.
     */
    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks; // return an empty arraylist of tasks if the file doesn't exist yet
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String taskLine = scanner.nextLine();
                Task task = parseTask(taskLine);

                if (task != null) {
                    tasks.add(task);
                }

            }
        } catch (Exception e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }

        return tasks;

    }

    /**
     * Creates a {@code Task} object based on the data in a line from the task data file.
     *
     * @param taskLine A line from the task data file in the format used by {@code Task.toSaveFormat()}.
     * @return A {@code Task} object corresponding to the line of data.
     */
    private static Task parseTask(String taskLine) {
        String[] taskParts = taskLine.split("\\|");
        Task task = null;

        switch (taskParts[0]) {
        case "T":
            task = new TodoTask(taskParts[2]);
            break;
        case "D":
            task = new DeadlineTask(taskParts[2], taskParts[3]);
            break;
        case "E":
            task = new EventTask(taskParts[2], taskParts[3], taskParts[4]);
            break;
        default:
            throw new NetherException("this should have never happened. What is going on..");
        }

        if (taskParts[1].equals("X")) {
            task.markAsDone();
        }
        return task;
    }
}
