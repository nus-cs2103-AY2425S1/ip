package milutrock;

import java.util.ArrayList;

import milutrock.tasks.Task;

/**
 * Manages a list of tasks, allowing for adding, removing, 
 * marking as done, and retrieving tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<Task>();

    /**
     * Add a Task object to a list of tasks.
     * 
     * @param task The task to add.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Remove a task at a specified index from the list of tasks and return the
     * removed task.
     * 
     * @param i Index of the task to remove.
     * @return The removed task.
     */
    public Task removeTask(int i) {
        Task task = tasks.get(i);
        this.tasks.remove(i);

        return task;
    }

    /**
     * Mark a task at index i as done.
     * 
     * @param i Index of the task to mark as done.
     */
    public void markTaskAsDone(int i) {
        tasks.get(i).markDone();
    }

    /**
     * Unmark a task at index i as done.
     * 
     * @param i Index of the task to unmark as done.
     */
    public void unmarkTaskAsDone(int i) {
        tasks.get(i).unmarkDone();
    }

    /**
     * Return the number of tasks in the list of tasks.
     * 
     * @return The number of tasks.
     */
    public int noOfTasks() {
        return tasks.size();
    }

    /**
     * Return the task at the specified index as a string.
     * 
     * @param i Index of the task to return.
     * @return String representation of the task.
     */
    public String taskAtIndexToString(int i) {
        return tasks.get(i).toString();
    }
}
