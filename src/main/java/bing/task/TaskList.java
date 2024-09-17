package bing.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
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
     * Constructs a TaskList with a list of tasks.
     *
     * @param tasks the list of tasks to initialize the TaskList with
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }


    /**
     * Adds a task to the list.
     *
     * @param task the task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }


    /**
     * Returns the task at the specified index.
     *
     * @param index the index of the task
     * @return the task at the specified index
     */
    public Task get(int index) {
        return tasks.get(index);
    }


    /**
     * Deletes the task at the specified index.
     *
     * @param index the index of the task to be deleted
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }


    /**
     * Returns the number of tasks in the list.
     *
     * @return the size of the task list
     */
    public int size() {
        return tasks.size();
    }


    /**
     * Returns the list of tasks.
     *
     * @return the list of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
