package bao.main;

import bao.task.Task;
import java.util.ArrayList;

/**
 * The TaskList class represents a list of tasks managed by the application, provides methods to add, delete, and
 * get tasks from the list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with a given ArrayList of tasks.
     *
     * @param tasks ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task to the list.
     *
     * @param index Index of the Task to be deleted in the list.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Returns the task at the index of the list.
     *
     * @param index Index of the task to return.
     * @return Task at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the list of tasks.
     *
     * @return The ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
