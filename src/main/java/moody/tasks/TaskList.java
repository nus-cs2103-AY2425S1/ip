package moody.tasks;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 * The TaskList class provides methods to manage and access a collection of Task objects.
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    /**
     * Creates an empty TaskList.
     * Initializes an empty list to store tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a TaskList with the specified tasks.
     * Initializes the TaskList with an existing list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Converts the TaskList to an ArrayList.
     * Provides access to the underlying list of tasks.
     *
     * @return The ArrayList containing all tasks in the TaskList.
     */
    public ArrayList<Task> toArrayList() {
        return this.tasks;
    }

    /**
     * Adds a task to the TaskList.
     * Appends the specified task to the end of the list.
     *
     * @param task The task to be added to the TaskList.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the TaskList by index.
     * Removes the task at the specified index and returns it.
     *
     * @param index The index of the task to be removed.
     * @return The task that was removed from the TaskList.
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Retrieves a task from the TaskList by index.
     * Returns the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the TaskList.
     * Provides the current size of the TaskList.
     *
     * @return The number of tasks in the TaskList.
     */
    public int size() {
        return tasks.size();
    }
}
