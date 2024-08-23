import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a storage which stores all the tasks.
 * Allows the user to save and load the list of tasks from a file.
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
     * Constructor for a Storage object with a file path.
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
     */
    private void parseTask(String taskData) {
        assert taskData != null : "Oops! Task data string cannot be null.";

        // Trim the taskData to remove leading/trailing spaces and split it by spaces
        String[] parts = taskData.trim().split(" ", 3);

        // Extract the task status (done or not)
        String status = parts[0];
        boolean isDone = status.equals("[X]");

        // Extract the task type (TODO, DEADLINE, EVENT)
        String taskType = parts[1].substring(1, parts[1].length() - 1); // Removes the square brackets

        // The rest is the description or additional info
        String description = parts[2];
        Task task = null;

        switch (taskType) {
            case "TODO":
                task = new Todo(description);
                break;
            case "DEADLINE":
                String[] deadlineParts = description.split(" \\(by: ");
                String deadlineDescription = deadlineParts[0].trim();
                String by = deadlineParts[1].replace(")", "").trim();
                task = new Deadline(deadlineDescription, by);
                break;
            case "EVENT":
                String[] eventParts = description.split(" \\(from: ");
                String eventDescription = eventParts[0].trim();
                String[] times = eventParts[1].replace(")", "").split(" to: ");
                String from = times[0].trim();
                String to = times[1].trim();
                task = new Event(eventDescription, from, to);
                break;
            default:
                System.out.println("Oops! Invalid task type detected.");
                return;
        }

        // Mark the task as done if applicable
        if (task != null) {
            if (isDone) {
                task.markAsDone(true);
            }
            this.taskList.addTask(task);
        } else {
            System.out.println("Oops! Failed to create the task from data: " + taskData);
        }
    }

    /**
     * Saves the list of tasks to the file.
     *
     * @param tasks The list of tasks.
     */
    public void saveTasks(ArrayList<Task> tasks) {
        assert tasks != null : "Oops! Task list should not be empty.";
        try {
            File file = new File(this.filePath);
            assert file != null : "Oops! File object cannot be null.";
            FileWriter writer = new FileWriter(file);

            for (Task task : tasks) {
                assert task != null : "Oops! Task object cannot be null.";
                writer.write(task.toString() + System.lineSeparator());
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
    public ArrayList<Task> loadTasks() {
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
                assert taskData != null : "Oops! Task data string cannot be null.";
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