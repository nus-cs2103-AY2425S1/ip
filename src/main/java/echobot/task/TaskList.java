package echobot.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds a task to the task list.
     * This method should be called after any operation that modifies the task list.
     *
     * @param task The task to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
        // to save after adding a task
    }

    /**
     * Removes a task from the task list at the specified index.
     * This method should be called after any operation that modifies the task list.
     *
     * @param index The index of the task to be removed.
     * @return The task that was removed from the list.
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    public Task removeTask(int index) {
        // to save after removing a task
        return tasks.remove(index);
    }

    /**
     * Returns the list of tasks.
     *
     * @return An ArrayList containing all tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return this.tasks.size();
    }
}
