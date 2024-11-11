package bimo.utils;

import java.util.ArrayList;

import bimo.tasks.Task;

/**
 * Represents the list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    /**
     * Instantiates a TaskList object with elements.
     *
     * @param tasks ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
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
    }

    /**
     * Removes a specified task from list.
     *
     * @param i Index of task to remove from list.
     * @return The Task object removed.
     */
    public Task removeTask(int i) {
        Task task = this.tasks.remove(i);
        return task;
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return Number of tasks in list.
     */
    public int getLength() {
        return this.tasks.size();
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

    /**
     *  Checks if task exists in the list.
     *
     * @param index Index of task to find.
     * @param tasks List of tasks.
     * @return True if task is inside list.
     */
    public boolean isTaskInList(int index, TaskList tasks) {
        boolean isExceedSize = index >= tasks.getLength();
        boolean isNegative = index < 0;
        if (isExceedSize || isNegative) {
            return false;
        }
        return true;
    }
}
