package bao.main;

import java.util.ArrayList;

import bao.task.Task;

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
        assert tasks != null : "Task list should not be null";
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to be added to the list.
     */
    public void addTask(Task task) {
        assert task != null : "Task should not be null";
        tasks.add(task);
    }

    /**
     * Deletes a task to the list.
     *
     * @param index Index of the Task to be deleted in the list.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public void deleteTask(int index) {
        assert index >= 0 && index < tasks.size() : "Index is out of bounds";
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
        assert index >= 0 && index < tasks.size() : "Index is out of bounds";
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

    /**
     * Finds tasks that contain the specified keyword.
     *
     * @param keyword Keyword to search for.
     * @return List of tasks that contain the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }
}
