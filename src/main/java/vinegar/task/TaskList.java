package vinegar.task;

import vinegar.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the list of tasks in the Vinegar application.
 * <p>
 * The TaskList class provides methods for adding, deleting, retrieving, and modifying tasks.
 * It maintains the current list of tasks and offers functionality for managing these tasks.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList initialized with an existing list of tasks.
     *
     * @param tasks A list of tasks to initialize the task list with.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to add. Cannot be null.
     * @throws NullPointerException If the task is null.
     */
    public void addTask(Task task) {
        if (task == null) {
            throw new NullPointerException("Task cannot be null");
        }
        tasks.add(task);
    }

    /**
     * Removes and returns the task at the specified index from the task list.
     *
     * @param index The index of the task to delete.
     * @return The removed task.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Retrieves the task at the specified index (an alias for {@link #getTask(int)}).
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the full list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns a string representation of all tasks in the task list.
     * Each task is represented on a new line, preceded by its position in the list.
     *
     * @return The string representation of the task list.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1)).append(".").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }

    public void clear() {
        tasks.clear();
    }
}
