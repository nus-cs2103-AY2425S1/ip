package stan;

import stan.tasks.Task;
import java.util.ArrayList;
import java.util.List;

/**
 * The TaskList class manages the list of tasks.
 * It provides methods to add, remove, and retrieve tasks, as well as to get the total number of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructor for TaskList.
     * Initializes an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor for TaskList.
     * Initializes the list with the provided tasks.
     *
     * @param tasks The list of tasks to initialize with.
     */
    public TaskList(ArrayList<Task> tasks) {
        // Assert that tasks is not null
        assert tasks != null : "tasks list should not be null";
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        // Assert that the task being added is not null
        assert task != null : "task to be added should not be null";
        tasks.add(task);
    }

    /**
     * Removes a task from the task list.
     *
     * @param index The index of the task to be removed.
     * @return The removed task.
     */
    public Task remove(int index) {
        // Assert that index is within valid range
        assert index >= 0 && index < tasks.size() : "Index out of bounds";
        return tasks.remove(index);
    }

    /**
     * Gets a task from the task list.
     *
     * @param index The index of the task to get.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        // Assert that index is within valid range
        assert index >= 0 && index < tasks.size() : "Index out of bounds";
        return tasks.get(index);
    }

    /**
     * Returns the size of the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Finds tasks that contain the specified keyword.
     *
     * @param keyword The keyword to search for.
     * @return A list of tasks that match the keyword.
     */
    public List<Task> findTasks(String keyword) {
        // Assert that the keyword is not null
        assert keyword != null : "Search keyword should not be null";
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            // Assert that each task is not null
            assert task != null : "Task in the list should not be null";
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
