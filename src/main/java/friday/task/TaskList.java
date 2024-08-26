package friday.task;

import friday.util.FridayException;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a new TaskList with the specified list of tasks.
     *
     * @param tasks An ArrayList of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs a new empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the TaskList at the specified index.
     *
     * @param index The index of the task to be deleted.
     * @throws FridayException If the index is out of range.
     */
    public void deleteTask(int index) throws FridayException {
        tasks.remove(index);
    }

    /**
     * Returns the list of tasks in the TaskList.
     *
     * @return An ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The number of tasks.
     */
    public int getSize() {
        return tasks.size();
    }
}
