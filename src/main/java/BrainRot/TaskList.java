package BrainRot;

import java.util.ArrayList;

/**
 * The TaskList class manages a collection of tasks.
 * It provides methods for adding, removing, and accessing tasks, as well as retrieving the size of the list.
 * The tasks are stored in an ArrayList.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     * This initializes the internal ArrayList to store tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with an existing list of tasks.
     * This is useful when loading tasks from storage.
     *
     * @param tasks An ArrayList of Task objects to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the TaskList.
     *
     * @param task The Task object to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the TaskList based on its index.
     *
     * @param index The index of the Task to be removed.
     * @throws IndexOutOfBoundsException If the index is out of range (index < 0 || index >= size()).
     */
    public void removeTask(int index) {
        tasks.remove(index);
    }

    /**
     * Retrieves a task from the TaskList based on its index.
     *
     * @param index The index of the Task to retrieve.
     * @return The Task object at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of range (index < 0 || index >= size()).
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks currently in the TaskList.
     *
     * @return The size of the TaskList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Retrieves the entire list of tasks.
     *
     * @return An ArrayList of all Task objects in the TaskList.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
