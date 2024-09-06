package elara.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     * Initializes the tasks list to an empty ArrayList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with a predefined list of tasks.
     *
     * @param tasks The list of tasks to be managed by this TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list by its index.
     *
     * @param index The index of the task to be removed from the list.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Marks a task as completed by its index.
     *
     * @param index The index of the task to be marked as completed.
     */
    public void markTask(int index) {
        tasks.get(index).mark();
    }

    /**
     * Unmarks a task as not completed by its index.
     *
     * @param index The index of the task to be marked as not completed.
     */
    public void unmarkTask(int index) {
        tasks.get(index).unmark();
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Retrieves a specific task by its index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the size of the task list.
     *
     * @return The number of tasks in the list.
     */
    public int getSize() {
        return tasks.size();
    }
}
