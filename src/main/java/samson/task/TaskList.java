package samson.task;

import java.util.ArrayList;
import java.util.List;

/**
 * The <code> TaskList </code> class represents a list of tasks. It provides methods to add,
 * delete, and retrieve tasks, as well as mark and unmark them as complete or incomplete.
 */
public class TaskList {
    private List<Task> tasks;
    private int index;

    /**
     * Constructs an empty <code> TaskList </code>.
     * It initializes the variables tasks and index
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.index = 0;
    }

    /**
     * Constructs a <code> TaskList </code> with an existing list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
        index = tasks.size();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
        index++;
    }

    /**
     * Deletes a task from the task list by its index.
     *
     * @param index The index of the task to be deleted.
     * @return The task that was removed.
     */
    public Task deleteTask(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds: " + index;

        Task removedTask = tasks.remove(index);
        this.index--;
        return removedTask;
    }

    /**
     * Retrieves a task by its index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the size of the task list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Marks a task as completed by its index.
     *
     * @param index The index of the task to be marked as done.
     */
    public void markTask(int index) {
        tasks.get(index).complete();
    }

    /**
     * Unmarks a task as incomplete by its index.
     *
     * @param index The index of the task to be marked as not done.
     */
    public void unmarkTask(int index) {
        tasks.get(index).notComplete();
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }
}
