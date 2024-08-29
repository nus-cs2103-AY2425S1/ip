package snipe.core;

import snipe.exception.SnipeException;
import snipe.task.Task;

import java.util.ArrayList;

/**
 * The {@code TaskList} class represents a list of tasks and provides methods
 * to manage tasks such as adding, deleting, retrieving tasks, and getting the size of the list.
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    /**
     * Constructs a new {@code TaskList} with an initial list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list at the specified index.
     *
     * @param index The index of the task to be deleted.
     * @throws SnipeException If the index is out of range (index &lt; 0 or index &ge; size of the list).
     */
    public void deleteTask(int index) throws SnipeException {
        if (index >= tasks.size() || index < 0) {
            throw new SnipeException("This list item does not exist!");
        }
        tasks.remove(index);
    }

    /**
     * Retrieves a task from the task list at the specified index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     * @throws SnipeException If the index is out of range (index &lt; 0 or index &ge; size of the list).
     */
    public Task getTask(int index) throws SnipeException {
        if (index >= tasks.size() || index < 0) {
            throw new SnipeException("This list item does not exist!");
        }
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the list of tasks.
     *
     * @return The {@link ArrayList} of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns a string representation of the number of tasks in the task list.
     *
     * @return A string indicating the number of tasks in the list.
     */
    public String listLength() {
        return String.format("\n Now you have %d %s in the list.", tasks.size(), tasks.size() == 1 ? "task" : "tasks");
    }
}
