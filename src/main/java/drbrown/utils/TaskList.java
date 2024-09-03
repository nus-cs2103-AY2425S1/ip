package drbrown.utils;

import drbrown.task.Task;

import java.util.ArrayList;

/**
 * Represents a list of tasks in the DrBrown application.
 * Provides methods to add, remove, and retrieve tasks from the list.
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasks The initial list of tasks to be included in the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param item The task to be added.
     */
    public void add(Task item) {
        tasks.add(item);
    }

    /**
     * Returns the list of tasks in the TaskList.
     *
     * @return An ArrayList containing all the tasks.
     */
    public ArrayList<Task> getList() {
        return tasks;
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The number of tasks in the list.
     */
    public int getCount() {
        return tasks.size();
    }

    /**
     * Removes a task from the TaskList based on its index.
     *
     * @param index The index of the task to be removed.
     */
    public void removeItem(int index) {
        tasks.remove(index);
    }
}
