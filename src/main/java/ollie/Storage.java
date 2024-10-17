package ollie;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
            List<String> parts = splitTaskData(taskData);
            String taskType = parts.get(0);
            String status = parts.get(1);
            String description = parts.get(2);
            String date = parts.size() > 3 ? parts.get(3) : "";
            String end = parts.size() > 4 ? parts.get(4) : "";

            Task task = createTask(taskType, description, date, end);
            task.markAsDone(status.equals("[X]"));
            taskList.addTaskWihoutMessage(task);

            return task;
        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            throw new OllieException("Oops! Error parsing task data: " + e.getMessage());
        }
    }

    /**
     * Splits the task data string into parts.
     *
     * @param taskData The task data string.
     * @return A list of task data parts.
     */
    private List<String> splitTaskData(String taskData) {
        return Arrays.stream(taskData.trim().split(" \\| "))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    /**
     * Creates a task based on the task type.
     *
     * @param taskType The type of the task.
     * @param description The description of the task.
     * @param date The start date of the task (if applicable).
     * @param end The end date of the task (if applicable).
     * @return A task object.
     * @throws OllieException If the task type is invalid.
     */
    private Task createTask(String taskType, String description, String date, String end) throws OllieException {
        switch (taskType) {
        case "TODO":
            return new Todo(description);
        case "DEADLINE":
            return new Deadline(description, LocalDateTime.parse(date, Task.getFormatDate()));
        case "EVENT":
            return new Event(description,
                    LocalDateTime.parse(date, Task.getFormatDate()),
                    LocalDateTime.parse(end, Task.getFormatDate()));
        default:
            throw new OllieException("Oops! Invalid task type detected: " + taskType);
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
