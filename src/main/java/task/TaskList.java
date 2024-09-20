package task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Constructor for TaskList class.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        assert tasks != null : "Tasks should be initialized";
    }

    /**
     * Constructor for TaskList class.
     *
     * @param tasks List of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to add.
     */
    public void add(Task task) {
        assert task != null : "Task to be added should not be null";
        tasks.add(task);
        assert tasks.contains(task) : "Task list should contain the added task";
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index Index of the task.
     * @return Task at the specified index.
     */
    public Task get(int index) {
        assert index >= 0 : "Index should be non-negative";
        assert index < tasks.size() : "Index should be within the size of the list";
        return tasks.get(index);
    }

    /**
     * Removes the task at the specified index.
     *
     * @param index Index of the task.
     * @return Removed task.
     */
    public Task remove(int index) {
        assert index >= 0 : "Index should be non-negative";
        assert index < tasks.size() : "Index should be within the size of the list";
        Task removedTask = tasks.remove(index);
        assert !tasks.contains(removedTask) : "Task list should not contain the removed task";
        return removedTask;
    }

    /**
     * Returns the size of the list of tasks.
     *
     * @return Size of the list of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns if the list of tasks is empty.
     *
     * @return True if the list of tasks is empty, otherwise false.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Finds tasks that contain the keyword.
     *
     * @param keyword The keyword to search for.
     * @return List of tasks that contain the keyword.
     */
    public TaskList findTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return new TaskList(matchingTasks);
    }
}
