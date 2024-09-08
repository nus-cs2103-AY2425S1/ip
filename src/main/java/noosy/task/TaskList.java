package noosy.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    /**
     * The list of tasks.
     */
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Gets the task at the specified index.
     *
     * @param i The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task get(int i) {
        return this.tasks.get(i);
    }

    /**
     * Removes the specified task from the list.
     *
     * @param task The task to be removed.
     */
    public void remove(Task task) {
        this.tasks.remove(task);
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Prints all tasks in the list to the console.
     */
    public void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
}
