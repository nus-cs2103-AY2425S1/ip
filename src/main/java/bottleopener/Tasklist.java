package bottleopener;

import java.util.ArrayList;

/**
 * The {@code BottleOpener.Tasklist} class manages a list of tasks, allowing operations such as adding,
 * deleting, marking, and unmarking tasks. It provides functionality to interact with the
 * task list and retrieve information about the tasks.
 */
public class Tasklist {
    private int index;
    private ArrayList<Task> tasklist;

    /**
     * Constructs an empty {@code BottleOpener.Tasklist} with an initial index of 0.
     */
    public Tasklist() {
        this.index = 0;
        this.tasklist = new ArrayList<Task>();
    }

    /**
     * Constructs a {@code BottleOpener.Tasklist} with a predefined list of tasks.
     *
     * @param tasklist The list of tasks to be managed.
     */
    public Tasklist(ArrayList<Task> tasklist) {
        this.index = tasklist.size();
        this.tasklist = tasklist;
    }

    /**
     * Adds a new task to the task list.
     *
     * @param toAdd The task to be added.
     */
    public void addTask(Task toAdd) {
        this.tasklist.add(toAdd);
        this.index++;
    }

    /**
     * Deletes a task from the task list based on its index.
     *
     * @param num The 1-based index of the task to be deleted.
     */
    public void deleteTask(int num) {
        this.tasklist.remove(num - 1);
        this.index = this.tasklist.size();
    }

    /**
     * Marks a task as done based on its index.
     *
     * @param num The 1-based index of the task to be marked as done.
     */
    public void markTask(int num) {
        Task markedTask = this.tasklist.get(num - 1).markAsDone();
        this.tasklist.set(num - 1, markedTask);
    }

    /**
     * Unmarks a task as not done based on its index.
     *
     * @param num The 1-based index of the task to be unmarked.
     */
    public void unmarkTask(int num) {
        Task unmarkedTask = this.tasklist.get(num - 1).markAsUndone();
        this.tasklist.set(num - 1, unmarkedTask);
    }

    /**
     * Returns the size of the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int getSize() {
        return this.tasklist.size();
    }

    /**
     * Retrieves a task based on its 0-based index.
     *
     * @param index The 0-based index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return this.tasklist.get(index);
    }

    /**
     * Returns a string representation of the entire task list.
     *
     * @return A formatted string of the task list with each task numbered.
     */
    public String showTasklist() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < this.tasklist.size(); i++) {
            output.append(String.format("%d. %s%n", i + 1, this.tasklist.get(i)));
        }
        return output.toString();
    }

}

