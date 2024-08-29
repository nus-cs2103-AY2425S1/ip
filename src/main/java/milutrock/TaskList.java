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
        Task task = this.tasks.get(i);
        this.tasks.remove(i);

        return task;
    }

    /**
     * Mark a task at index i as done.
     * 
     * @param i Index of the task to mark as done.
     */
    public void markTaskAsDone(int i) {
        this.tasks.get(i).markDone();
    }

    /**
     * Unmark a task at index i as done.
     * 
     * @param i Index of the task to unmark as done.
     */
    public void unmarkTaskAsDone(int i) {
        this.tasks.get(i).unmarkDone();
    }

    /**
     * Return the number of tasks in the list of tasks.
     * 
     * @return The number of tasks.
     */
    public int getNumberOfTasks() {
        return this.tasks.size();
    }

    /**
     * Return the task at the specified index as a string.
     * 
     * @param i Index of the task to return.
     * @return String representation of the task.
     */
    public String getTaskAtIndexAsString(int i) {
        return this.tasks.get(i).toString();
    }

    /**
     * Return list of tasks with names that contain a specific query string.
     * 
     * @param query String to search for.
     * @return ArrayList of tasks that match the query.
     */
    public ArrayList<Task> getTasksFromSearchString(String query) {
        ArrayList<Task> matchingTasks = new ArrayList<Task>();
        for (Task task : this.tasks) {
            if (task.toString().contains(query)) {
                matchingTasks.add(task);
            }
        }

        return matchingTasks;
    }
}
