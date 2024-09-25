package bangmang.tasks;

import java.util.ArrayList;
import bangmang.exception.InvalidTaskFormatException;

/**
 * The TaskList class manages the list of tasks, providing methods to add, delete,
 * mark as done, and retrieve tasks.
 */

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list given its index.
     *
     * @param index The index of the task to delete.
     * @throws InvalidTaskFormatException if the index is out of range.
     */
    public void delete(int index) throws InvalidTaskFormatException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskFormatException("Alamak, task number out of range.");
        }
        tasks.remove(index);
    }

    /**
     * Returns a task from the task list given its index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     * @throws InvalidTaskFormatException if the index is out of range.
     */
    public Task get(int index) throws InvalidTaskFormatException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskFormatException("Alamak, task number out of range.");
        }
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the ArrayList of tasks.
     *
     * @return The ArrayList containing all tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Marks a task as done given its index.
     *
     * @param index The index of the task to mark as done.
     * @throws InvalidTaskFormatException if the index is out of range.
     */
    public void markTask(int index) throws InvalidTaskFormatException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskFormatException("Alamak, task number out of range.");
        }
        Task t = tasks.get(index).markTask();
        tasks.set(index, t);
    }

    /**
     * Marks a task as undone given its index.
     *
     * @param index The index of the task to mark as undone.
     * @throws InvalidTaskFormatException if the index is out of range.
     */
    public void unmarkTask(int index) throws InvalidTaskFormatException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskFormatException("Alamak, task number out of range.");
        }
        Task t = tasks.get(index).unmarkTask();
        tasks.set(index, t);
    }
}
