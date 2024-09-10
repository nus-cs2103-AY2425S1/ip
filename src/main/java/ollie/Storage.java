package ollie;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

import ollie.exception.OllieException;
import ollie.task.Deadline;
import ollie.task.Event;
import ollie.task.Task;
import ollie.task.TaskList;
import ollie.task.Todo;

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
        assert taskData != null : "Oops! Task data string cannot be empty.";

        try {
            // Split the taskData by " | " and collect parts into a list
            List<String> parts = Arrays.stream(taskData.trim().split(" \\| "))
                    .map(String::trim)
                    .toList();

            // Extract information from parts
            String taskType = parts.get(0);
            String status = parts.get(1);
            String description = parts.get(2);
            String date = parts.size() > 3 ? parts.get(3) : "";
            String end = parts.size() > 4 ? parts.get(4) : "";

            boolean isDone = status.equals("[X]");

            // Map task type to task creation function
            Function<List<String>, Task> taskCreator;
            switch (taskType) {
            case "TODO":
                taskCreator = p -> new Todo(description);
                break;
            case "DEADLINE":
                taskCreator = p -> new Deadline(description, LocalDateTime.parse(date, Task.getFormatDate()));
                break;
            case "EVENT":
                taskCreator = p -> new Event(description,
                        LocalDateTime.parse(date, Task.getFormatDate()),
                        LocalDateTime.parse(end, Task.getFormatDate()));
                break;
            default:
                throw new OllieException("Oops! Invalid task type detected: " + taskType);
            }

            // Create the task using the mapped function
            Task task = taskCreator.apply(parts);
            task.markAsDone(isDone);
            taskList.addTaskWihoutMessage(task);

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
        assert tasks != null : "Oops! Task list is empty.";
        try {
            File file = new File(this.filePath);
            assert file != null : "File object cannot be null.";
            FileWriter writer = new FileWriter(file);

            for (Task task : tasks) {
                assert task != null : "Oops! Task object cannot be null.";
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
                boolean isCreated = file.createNewFile();
                assert isCreated : "Oops! A file should be created for the task list.";
                return this.taskList.getTasks();
            }

            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String taskData = fileScanner.nextLine();
                assert taskData != null : "Oops! Task data cannot be null.";
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
