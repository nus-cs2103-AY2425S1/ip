package conversage.task;

import conversage.exception.ConverSageException;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param tasks the list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the list at the specified index.
     *
     * @param index the index of the task to be deleted.
     * @throws ConverSageException if the index is invalid.
     */
    public void deleteTask(int index) throws ConverSageException {
        if (index < 0 || index >= tasks.size()) {
            throw new ConverSageException("Invalid task number!");
        }
        assert index >= 0 && index < tasks.size() : "Task index should be within valid range";
        tasks.remove(index);
    }

    /**
     * Gets a task from the list at the specified index.
     *
     * @param index the index of the task to be retrieved.
     * @return the task at the specified index.
     * @throws ConverSageException if the index is invalid.
     */
    public Task getTask(int index) throws ConverSageException {
        if (index < 0 || index >= tasks.size()) {
            throw new ConverSageException("Invalid task number!");
        }
        assert index >= 0 && index < tasks.size() : "Task index should be within valid range";
        return tasks.get(index);
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return the number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Gets the list of tasks.
     *
     * @return the list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }


}
