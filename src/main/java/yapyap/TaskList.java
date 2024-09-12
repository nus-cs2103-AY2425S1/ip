package yapyap;

import java.util.ArrayList;

/**
 * Represents a list of tasks. Provides operations to add, delete, and retrieve tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a TaskList with the given list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the TaskList.
     *
     * @param task The task to be deleted.
     */
    public void deleteTask(Task task) {
        this.tasks.remove(task);
    }

    /**
     * Retrieves a task at the specified index from the TaskList.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The size of the TaskList.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns the list of tasks in the TaskList.
     *
     * @return The ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
