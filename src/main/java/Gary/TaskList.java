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

    // List to store tasks
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
        while (sc.hasNextLine()) {
            String nextLine = sc.nextLine();
            String[] split = nextLine.split(" \\| ");

            // Assertion: Ensure that the split line has the correct number of elements
            assert split.length >= 3 : "Invalid task format";

            String taskType = split[0].trim();
            boolean isDone = split[1].equals("1");

            switch (taskType) {
            case "T":
                Task todo = new ToDo(split[2].trim());
                tasks.add(todo);
                if (isDone) {
                    todo.editStatus();
                }
                break;
            case "D":
                // Assertion: Ensure the deadline task format has the correct number of fields
                assert split.length >= 4 : "Invalid deadline task format";
                Task deadline = new Deadline(split[2].trim(), split[3].trim());
                tasks.add(deadline);
                if (isDone) {
                    deadline.editStatus();
                }
                break;
            case "E":
                // Assertion: Ensure the event task format has the correct number of fields
                assert split.length >= 5 : "Invalid event task format";
                Task event = new Event(split[2].trim(), split[3].trim(), split[4].trim());
                tasks.add(event);
                if (isDone) {
                    event.editStatus();
                }
                break;
            default:
                // Handle invalid task type if necessary
                assert false : "Unknown task type: " + taskType;
                break;
            }
        }
        sc.close();
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
