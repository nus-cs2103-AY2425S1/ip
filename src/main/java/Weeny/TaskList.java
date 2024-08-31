package weeny;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a list of tasks, allowing addition, deletion, and status updates.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Initializes a new TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the list by its index.
     *
     * @param index The index of the task to remove.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Marks a task as done by its index.
     *
     * @param index The index of the task to mark.
     */
    public void markAsDone(int index) {
        tasks.get(index).setAsDone();
    }

    /**
     * Unmarks a task as not done by its index.
     *
     * @param index The index of the task to unmark.
     */
    public void markAsNotDone(int index) {
        tasks.get(index).setAsNotDone();
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns a task by its index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }
}
