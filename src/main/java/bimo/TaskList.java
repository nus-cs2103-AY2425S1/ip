package bimo;

import bimo.tasks.Task;

import java.util.ArrayList;

/**
 * Contains the list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    private static int length = 0;

    /**
     * Instantiates a TaskList object with elements.
     *
     * @param tasks ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        length = tasks.size();
    }

    /**
     * Instantiates a TaskList object with no elements.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task object to the list of tasks.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        length += 1;
    }

    /**
     * Removes a specified task from list.
     *
     * @param i Index of task to remove from list.
     * @return The Task object removed.
     */
    public Task removeTask(int i) {
        Task task = this.tasks.remove(i);
        length -= 1;
        return task;
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return Number of tasks in list.
     */
    public int getLength() {
        return length;
    }

    /**
     * Returns the specified task.
     *
     * @param i Index of specified task.
     * @return Task at index i.
     */
    public Task getTask(int i) {
        return this.tasks.get(i);
    }
}
