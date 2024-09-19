package Gary;

import java.util.ArrayList;
import java.util.Scanner;

import Gary.task.Deadline;
import Gary.task.Event;
import Gary.task.Task;
import Gary.task.ToDo;

/**
 * The {@code TaskList} class manages a list of tasks, providing functionalities
 * to add, remove, and retrieve tasks. It also supports initializing tasks from a file.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a {@code TaskList} by loading tasks from a {@code Scanner} object.
     * This constructor reads each line from the scanner to initialize tasks.
     *
     * @param sc The {@code Scanner} object containing task data.
     */
    public TaskList(Scanner sc) {
        // Assertion: Ensure that the Scanner object is not null
        assert sc != null : "Scanner cannot be null";

        this.tasks = new ArrayList<>();
        loadTasksFromScanner(sc);
    }

    /**
     * Loads tasks from the given {@code Scanner} and adds them to the task list.
     *
     * @param sc The {@code Scanner} object containing task data.
     */
    private void loadTasksFromScanner(Scanner sc) {
        while (sc.hasNextLine()) {
            String nextLine = sc.nextLine();
            try {
                Task task = parseTask(nextLine);
                tasks.add(task);
            } catch (IllegalArgumentException e) {
                // Handle invalid task format or missing fields
                System.err.println("Error parsing task: " + e.getMessage());
            }
        }
        sc.close();
    }

    /**
     * Parses a task from a line of text.
     *
     * @param line The line of text to parse.
     * @return The parsed {@code Task} object.
     * @throws IllegalArgumentException if the task format is invalid.
     */
    private Task parseTask(String line) {
        String[] split = line.split(" \\| ");
        if (split.length < 3) {
            throw new IllegalArgumentException("Invalid task format");
        }

        String taskType = split[0].trim();
        boolean isDone = split[1].equals("1");
        Task task;

        switch (taskType) {
        case "T":
            task = new ToDo(split[2].trim());
            break;
        case "D":
            if (split.length < 4) {
                throw new IllegalArgumentException("Deadline task missing fields");
            }
            task = new Deadline(split[2].trim(), split[3].trim());
            break;
        case "E":
            if (split.length < 5) {
                throw new IllegalArgumentException("Event task missing fields");
            }
            task = new Event(split[2].trim(), split[3].trim(), split[4].trim());
            break;
        default:
            throw new IllegalArgumentException("Unknown task type: " + taskType);
        }

        if (isDone) {
            task.editStatus();
        }

        return task;
    }

    /**
     * Adds a task to the {@code TaskList}.
     *
     * @param task The {@code Task} object to add.
     */
    public void addTask(Task task) {
        // Assertion: Ensure the task is not null
        assert task != null : "Task cannot be null";

        this.tasks.add(task);
    }

    /**
     * Removes a task from the {@code TaskList} at the specified index.
     *
     * @param index The index of the task to remove.
     * @return The removed {@code Task} object.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public Task removeTask(int index) {
        // Assertion: Ensure the index is within the valid range
        assert index >= 0 && index < this.tasks.size() : "Invalid index for removing task";

        return this.tasks.remove(index);
    }

    /**
     * Retrieves the task at the specified index from the {@code TaskList}.
     *
     * @param index The index of the task to retrieve.
     * @return The {@code Task} object at the specified index.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public Task getTask(int index) {
        // Assertion: Ensure the index is within the valid range
        assert index >= 0 && index < this.tasks.size() : "Invalid index for retrieving task";

        return this.tasks.get(index);
    }

    /**
     * Returns the number of tasks in the {@code TaskList}.
     *
     * @return The size of the {@code TaskList}.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns a string representation of the {@code TaskList}.
     *
     * @return A string representation of the {@code TaskList}.
     */
    @Override
    public String toString() {
        return this.tasks.toString();
    }
}
