package ollie;

import ollie.exception.OllieException;
import ollie.task.Deadline;
import ollie.task.Event;
import ollie.task.Task;
import ollie.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;

/**
 * The Storage class represents a storage which stores all the tasks.
 * It allows the user to save and load the list of tasks from a file.
 */
public class Storage {

    /**
     * Represents the path of the file that stores the list of tasks.
     */
    private String filePath;

    /**
     * Represents the list of tasks.
     */
    private TaskList taskList = new TaskList();

    /**
     * Constructor for a ollie.Storage object with a file path.
     *
     * @param filePath The path of the file that stores the list of tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        assert filePath != null : "Oops! Folder does not exist yet.";
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Oops! An error occurred when creating the file: " + e.getMessage());
            }
        }
    }

    /**
     * Parses the task data string and creates a task object.
     *
     * @param taskData The task data string.
     * @return task object.
     */
    private Task parseTask(String taskData) throws OllieException {
        assert taskData != null : "Oops! ollie.task.Task data string cannot be empty.";

        try {
            // Split the taskData by " | "
            String[] parts = taskData.trim().split(" \\| ");

            // Extract information: task type, status, description, by or from, and end
            String taskType = parts[0].trim();
            String status = parts[1].trim();
            String description = parts[2].trim();
            String date = parts.length > 3 ? parts[3].trim() : "";
            String end = parts.length > 4 ? parts[4].trim() : "";

            boolean isDone = status.equals("[X]");

            Task task = null;

            switch (taskType) {
            case "TODO":
                task = new Todo(description);
                break;
            case "DEADLINE":
                task = new Deadline(description, LocalDateTime.parse(date, Task.getFormatDate()));
                break;
            case "EVENT":
                task = new Event(description, LocalDateTime.parse(date, Task.getFormatDate()),
                        LocalDateTime.parse(end, Task.getFormatDate()));
                break;
            default:
                throw new OllieException("Oops! Invalid task type detected: " + taskType);
            }

            if (task != null) {
                task.markAsDone(isDone);
                taskList.addTaskWihoutMessage(task);
            }

            return task;
        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            throw new OllieException("Oops! Error parsing task data: " + e.getMessage());
        }
    }

    /**
     * Saves the list of tasks to the file.
     *
     * @param tasks The list of tasks.
     */
    public void saveTasks(ArrayList<Task> tasks) {
        assert tasks != null : "Oops! ollie.task.Task list is empty.";
        try {
            File file = new File(this.filePath);
            assert file != null : "Oops! File object cannot be null.";
            FileWriter writer = new FileWriter(file);

            for (Task task : tasks) {
                assert task != null : "Oops! ollie.task.Task object cannot be null.";
                writer.write(task.saveAsString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Oops! An error occurred when saving the list of tasks: " + e.getMessage());
        }
    }

    /**
     * Loads the list of tasks from the file.
     * If the file does not exist, create a new file.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> loadTasks() throws OllieException {
        try {
            File file = new File(filePath);
            assert file != null : "Oops! File cannot be null.";

            if (!file.exists()) {
                boolean created = file.createNewFile();
                assert created : "Oops! Failed to create a new file for the list of tasks.";
                return this.taskList.getTasks();
            }

            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String taskData = fileScanner.nextLine();
                assert taskData != null : "Oops! ollie.task.Task data string cannot be null.";
                parseTask(taskData);
            }
            fileScanner.close();
            return this.taskList.getTasks();
        } catch (IOException e) {
            System.out.println("Oops! Error loading the list of tasks from file: " + e.getMessage());
        }
        return null;
    }
}