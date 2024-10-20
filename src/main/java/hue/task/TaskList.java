package hue.task;

import java.util.ArrayList;
/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    /**
     * Creates an empty {@code TaskList}.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    /**
     * Creates a {@code TaskList} with the specified list of tasks.
     *
     * @param tasks The list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }
    /**
     * Retrieves a task from the list by index.
     *
     * @param index The index of the task.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }
    /**
     * Gets the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }
    /**
     * Removes a task from the list by index.
     *
     * @param index The index of the task to be removed.
     * @return The task that was removed.
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }
    /**
     * Retrieves the list of tasks.
     *
     * @return An {@code ArrayList} of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
