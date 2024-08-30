package talkie.task;

import java.util.ArrayList;

/**
 * Manages a list of tasks in the Talkie application.
 * <p>
 * The {@code TaskList} class provides methods to add, remove, and access tasks. It maintains an internal list
 * of {@code Task} objects and supports various operations such as retrieving the size of the list and checking
 * if the list is empty.
 * </p>
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
     * Constructs a {@code TaskList} with the specified list of tasks.
     *
     * @param tasks An {@code ArrayList} of {@code Task} objects to initialize the task list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Retrieves the task at the specified index.
     * <p>
     * The index is 1-based, so the first task is at index 1.
     * </p>
     *
     * @param index The index of the task to retrieve (1-based).
     * @return The {@code Task} at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public Task getTask(int index) {
        return this.tasks.get(index - 1);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Adds a task to the end of the list.
     *
     * @param task The {@code Task} to add to the list.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes and returns the task at the specified index.
     * <p>
     * The index is 1-based, so the first task is at index 1.
     * </p>
     *
     * @param index The index of the task to remove (1-based).
     * @return The {@code Task} that was removed from the list.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public Task deleteTask(int index) {
        return this.tasks.remove(index - 1);
    }

    /**
     * Checks if the task list is empty.
     *
     * @return {@code true} if the list is empty, {@code false} otherwise.
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }
}
