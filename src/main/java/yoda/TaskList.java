package yoda;

import java.util.ArrayList;

import yoda.tasks.Task;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    /**
     * Creates a TaskList with a given list of tasks.
     *
     * @param tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Retrieves the number of tasks in the TaskList.
     *
     * @return The number of tasks in the TaskList.
     */
    public int getLength() {
        return tasks.size();
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index The index of the task to delete.
     */
    public void delete(int index) {
        tasks.remove(index);
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to add.
     */
    public void add(Task task) {
        tasks.add(task);
    }
}
