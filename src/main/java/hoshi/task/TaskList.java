package hoshi.task;

import java.util.ArrayList;

/**
 * TaskList class that handles 3 types of tasks
 */
public class TaskList {

    /**
     * Stores the tasks in an ArrayList.
     */
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with an initial list of tasks.
     *
     * @param tasks the initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs a TaskList with an initial list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Adds the given task to the task list.
     *
     * @param task the task to be added to the list.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Returns the task at the provided index.
     *
     * @param index the index to be retrieved from the list.
     * @return Task the task of the specified index.
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Deletes a task in the taskList given an index.
     *
     * @param index the index of the task to be deleted.
     */
    public void delete(int index) {

        this.tasks.remove(index);
    }

    /**
     * Returns the size of the task list.
     *
     * @return the size of the task list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns true if there are no tasks and false if there is at least 1 task in the list.
     *
     * @return a boolean indicating true if empty and false if not empty
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Returns a string representation of all stored tasks.
     *
     * <p>The string includes the status icon and the description of each task.</p>
     *
     */
    @Override
    public String toString() {
        // format all tasks as a string before printing
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            result.append(i + 1).append(". ").append(this.tasks.get(i).toString()).append("\n");
        }
        return result.toString();
    }

}
