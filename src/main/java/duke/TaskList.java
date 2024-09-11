package duke;

import java.util.ArrayList;

/**
 * Manages a list of tasks. This class provides methods to add, delete,
 * retrieve, and get information about tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    private int taskCount;

    /**
     * Constructs a TaskList with an initial list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.taskCount = tasks.size();
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
        taskCount++;
    }

    /**
     * Deletes a task from the list at the specified index.
     *
     * @param index The index of the task to be deleted.
     * @throws MeowException If the index is out of bounds.
     */
    public void deleteTask(int index) throws MeowException {
        assert index >= 0 && index < taskCount : "Task index out of bounds: " + index;

        // if block is for double protection
        if (index >= 0 && index < taskCount) {
            tasks.remove(index);
            taskCount--;
        } else {
            throw new MeowException("Invalid task number.");
        }
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     * @throws MeowException If the index is out of bounds.
     */
    public Task getTask(int index) throws MeowException {
        assert index >= 0 && index < taskCount : "Task index out of bounds: " + index;

        // if block is for double protection
        if (index >= 0 && index < taskCount) {
            return tasks.get(index);
        } else {
            throw new MeowException("Invalid task number.");
        }
    }

    /**
     * @return The number of tasks in list.
     */
    public int getTaskCount() {
        return taskCount;
    }

    /**
     * Returns full task list.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
