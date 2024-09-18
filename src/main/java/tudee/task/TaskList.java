package tudee.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks.
 * Provides methods to add, retrieve, and delete tasks from the list.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs a TaskList with a given list of tasks.
     *
     * @param taskList The initial list of tasks.
     */
    public TaskList(List<Task> taskList) {
        this.tasks = taskList;
        // Assert that the list is initalised correctly.
        assert tasks != null : "Task list should not be null";
    }

    /**
     * Constructs an empty TaskList if no parameter specified.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        // Assert that the list is initalised correctly.
        assert tasks != null : "Task list should be initalised as an empty list";
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        // Assert that the task being added is not null.
        assert task != null : "Task to add should not be null";
        tasks.add(task);
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        // Assert that the index is within the valid range.
        assert index >= 0 && index < tasks.size() : "Index out of bounds";
        return tasks.get(index);
    }

    /**
     * Retrieves the entire list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index The index of the task to delete.
     * @return The deleted task.
     */
    public Task deleteTask(int index) {
        // Assert that the index is within the valid range.
        assert index >= 0 && index < tasks.size() : "Index out of bounds";
        return tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int numOfTasks() {
        return tasks.size();
    }
}
