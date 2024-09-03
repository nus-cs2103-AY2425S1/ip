package zero.task;

import java.util.ArrayList;

import zero.exception.ZeroException;

/**
 * The {@code TaskList} class represents a list of tasks.
 * It provides methods to add, delete, and retrieve tasks, as well as to validate task indices.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a {@code TaskList} with the specified list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
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
     * Deletes the task at the specified index from the task list.
     *
     * @param index The index of the task to be deleted, the offset has been factored in.
     * @return The task that was deleted.
     * @throws ZeroException If the index is out of range.
     */
    public Task deleteTask(int index) throws ZeroException {
        validateIndex(index);
        Task item = tasks.get(index);
        tasks.remove(index);
        return item;
    }

    /**
     * Retrieves the task at the specified index from the task list.
     *
     * @param index The index of the task to be retrieved, the offset has been factored in.
     * @return The task at the specified index.
     * @throws ZeroException If the index is out of range.
     */
    public Task getTask(int index) throws ZeroException {
        validateIndex(index);
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The size of the task list.
     */
    public int getSize() {
        return tasks.size();
    }

    private void validateIndex(int index) throws ZeroException {
        if (index < 0 || index >= tasks.size()) {
            throw new ZeroException("The task number is out of range.");
        }
    }
}
