package taskon.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasks An ArrayList of tasks to initialize the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the TaskList by its index.
     *
     * @param index The index of the task to delete.
     */
    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    /**
     * Retrieves a task from the TaskList by its index.
     *
     * @param index The index of the task to retrieve.
     * @return The Task object at the specified index.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The size of the TaskList.
     */
    public int size() {
        return tasks.size();
    }
}
