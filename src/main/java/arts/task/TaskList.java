package arts.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks, providing functionalities to manage
 * tasks such as adding, retrieving, removing, and checking the list's size.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        assert tasks != null : "Tasks list cannot be null";
        this.tasks = tasks;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        assert task != null : "Task to be added cannot be null";
        tasks.add(task);
    }

    /**
     * Retrieves a task at the specified index from the TaskList.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds";
        return tasks.get(index);
    }

    /**
     * Removes and returns the task at the specified index from the TaskList.
     *
     * @param index The index of the task to remove.
     * @return The task that was removed.
     */
    public Task removeTask(int index) {
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
     * Checks if the TaskList is empty.
     *
     * @return true if the TaskList is empty, otherwise false.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the list of tasks in the TaskList.
     *
     * @return An ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        assert tasks != null : "Tasks list should not be null";
        return tasks;
    }
}
