package sammy.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks. Provides methods to add, remove, and access tasks.
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
     * Constructs a TaskList with a predefined list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(List<Task> tasks) {
        assert tasks != null : "Task list cannot be null";
        this.tasks = tasks;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        assert task != null : "Task to be added cannot be null";
        tasks.add(task);
    }

    /**
     * Removes and returns the task at the specified index.
     *
     * @param index The index of the task to be removed.
     * @return The task that was removed.
     */
    public Task remove(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds";
        return tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The size of the TaskList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds";
        return tasks.get(index);
    }

    /**
     * Returns the list of all tasks.
     *
     * @return A list of all tasks in the TaskList.
     */
    public List<Task> getAllTasks() {
        return tasks;
    }
}

