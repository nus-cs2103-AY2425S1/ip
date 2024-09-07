package noisy;

import java.util.ArrayList;

/**
 * Manages a list of tasks, providing methods to add, remove, and access tasks.
 * It maintains an internal list of {@code Task} objects and allows manipulation of the list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }


    /**
     * Adds a task to the TaskList.
     *
     * @param task The {@code Task} to be added to the list.
     */
    public void addToList(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes a task from the TaskList by its index.
     *
     * @param i The index of the task to be removed.
     */
    public void deleteFromList(int i) {
        this.tasks.remove(i);
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The size of the task list.
     */
    public int getListSize() {
        return this.tasks.size();
    }

    /**
     * Retrieves a task from the TaskList by its index.
     *
     * @param i The index of the task to be retrieved.
     * @return The {@code Task} at the specified index.
     */
    public Task getTask(int i) {
        return this.tasks.get(i);
    }

    /**
     * Marks a task as done by its index in the TaskList.
     *
     * @param i The index of the task to be marked as done.
     */
    public void markDoneFromList(int i) {
        this.tasks.get(i).markDone();
    }

    /**
     * Returns the list of all tasks in the TaskList.
     *
     * @return An {@code ArrayList} of all tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

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
