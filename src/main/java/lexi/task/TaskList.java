package lexi.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks in the Lexi application.
 * This class provides methods to add, delete, update, and retrieve tasks from the list.
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
    }

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        // Precondition: Ensure the provided task list is not null
        assert tasks != null : "Task list cannot be null.";
        this.tasks = tasks;
    }

    /**
     * Deletes the task at the specified index from the list.
     *
     * @param index The index of the task to be deleted.
     * @return The task that was removed.
     */
    public Task deleteTask(int index) {
        // Precondition: Ensure the index is within valid range
        assert index >= 0 && index < tasks.size() : "Index out of bounds.";
        return tasks.remove(index);
    }

    /**
     * Adds a task to the end of the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        // Precondition: Ensure the task to be added is not null
        assert task != null : "Task cannot be null.";
        tasks.add(task);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        // Precondition: Ensure the index is within valid range
        assert index >= 0 && index < tasks.size() : "Index out of bounds.";
        return tasks.get(index);
    }

    /**
     * Updates the task at the specified index with a new task.
     *
     * @param task The new task to replace the old one.
     * @param taskNumber The index of the task to be updated.
     */
    public void updateTask(Task task, int taskNumber) {
        // Precondition: Ensure the task to be updated is not null
        assert task != null : "Task cannot be null.";
        // Precondition: Ensure the index is within valid range
        assert taskNumber >= 0 && taskNumber < tasks.size() : "Index out of bounds.";

        tasks.set(taskNumber, task);
    }
}
