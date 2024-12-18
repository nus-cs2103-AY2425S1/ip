package barry.util;

import barry.task.Task;

import java.util.ArrayList;

/**
 * Represents a list of all tasks.
 */
public class TaskList {
    private ArrayList<Task> allTasks;

    /**
     * Constructs an empty TaskList.
     * Initialises the task list as an empty ArrayList.
     */
    public TaskList() {
        this.allTasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param allTasks The list of all tasks.
     */
    public TaskList(ArrayList<Task> allTasks) {
        this.allTasks = allTasks;
    }

    /**
     * Adds a task to the list containing all tasks.
     *
     * @param task The task to be added to the list of all tasks.
     */
    public void addTask(Task task) {
        assert task != null : "Task cannot be null!";
        allTasks.add(task);
    }

    /**
     * Removes a task from the list of all tasks
     *
     * @param i The index of the task to be removed from the task list.
     * @return The task that was removed from the task list.
     */
    public Task removeTask(int i) {
        assert i >= 0 && i < allTasks.size() : "index is out of valid range of tasks :(";
        return allTasks.remove(i);
    }

    /**
     * Retrieves a task from the task list.
     *
     * @param i The index of the task to be retrieved from the task list.
     * @return The task to be retrieved.
     */
    public Task getTask(int i) {
        assert i >= 0 && i < allTasks.size() : "Index out of bounds: " + i;
        return allTasks.get(i);
    }

    /**
     * Returns the total number of tasks in the task list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return allTasks.size();
    }

    /**
     * Returns the entire list of tasks.
     *
     * @return An ArrayList containing all the tasks in the task list.
     */
    public ArrayList<Task> getAllTasks() {
        return this.allTasks;
    }

}
