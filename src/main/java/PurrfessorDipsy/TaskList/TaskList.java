package PurrfessorDipsy.TaskList;

import PurrfessorDipsy.Storage.Storage;
import PurrfessorDipsy.Task.Task;

import java.util.ArrayList;

/**
 * Represents a list of tasks. The TaskList class manages the collection of tasks,
 * allowing tasks to be added, removed, retrieved, and saved to local storage.
 */
public class TaskList {

    /** The internal list of tasks managed by this TaskList. */
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty {@code TaskList}.
     * This constructor initializes the task list with an empty list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a {@code TaskList} with the specified list of tasks.
     * This constructor allows initializing the task list with an existing collection of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the internal list of tasks.
     * This method provides access to the underlying list of tasks.
     *
     * @return The list of tasks managed by this TaskList.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Adds a new task to the list and saves the updated list to local storage.
     *
     * @param task The task to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
        Storage.save(tasks);
    }

    /**
     * Deletes the task at the specified index from the list and saves the updated list to local storage.
     *
     * @param index The index of the task to be removed from the list.
     * @return The task that was removed from the list.
     */
    public Task deleteTask(int index) {
        Task removedTask = tasks.remove(index);
        Storage.save(tasks);
        return removedTask;
    }

    /**
     * Retrieves the task at the specified index from the list.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Saves the current list of tasks to local storage.
     * This method is used to persist the task list to disk.
     */
    public void saveToLocalDisk() {
        Storage.save(tasks);
    }
}
