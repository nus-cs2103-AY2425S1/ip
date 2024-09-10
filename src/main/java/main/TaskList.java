package main;

import tasks.Task;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a list of tasks and provides methods to manipulate the tasks.
 * This class acts as a wrapper around a {@code List<Task>}, allowing for easy task management.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        this.tasks = new ArrayList<>(); // Initialize with an empty list
    }

    /**
     * Constructs a {@code TaskList} with the specified list of tasks.
     *
     * @param tasks the list of tasks to initialize the task list with
     */
    public TaskList(List<Task> tasks) {
        assert tasks != null : "Task list should not be null";
        this.tasks = tasks; // Initialize with provided tasks
    }

    /**
     * Adds a task to the task list.
     *
     * @param task the task to be added
     */
    public void addTask(Task task) {
        assert task != null : "Task to be added should not be null";
        tasks.add(task);
    }

    /**
     * Removes a task from the task list at the specified index.
     *
     * @param index the index of the task to be removed
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void deleteTask(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds: " + index;
        tasks.remove(index);
    }

    /**
     * Retrieves a task from the task list at the specified index.
     *
     * @param index the index of the task to retrieve
     * @return the task at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public Task get(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds: " + index;
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return the number of tasks
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the list of all tasks.
     *
     * @return the list of tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }
}
