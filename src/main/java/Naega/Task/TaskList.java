package Naega.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     * Initializes an ArrayList to store tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        assert tasks != null : "Task list should be initialized";
    }

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param tasks the initial list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        assert tasks != null : "Initial task list must not be null";
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the TaskList.
     *
     * @param task the task to add
     */
    public void addTask(Task task) {
        assert task != null : "Task to add must not be null";
        tasks.add(task);
    }

    /**
     * Deletes the task at the specified index from the TaskList.
     *
     * @param index the index of the task to delete
     */
    public void deleteTask(int index) {
        assert index >= 0 && index < tasks.size() : "Index to delete must be valid";
        tasks.remove(index);
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index the index of the task to retrieve
     * @return the task at the specified index
     */
    public Task getTask(int index) {
        assert index >= 0 && index < tasks.size() : "Index to retrieve must be valid";
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return the number of tasks
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Finds and returns a list of tasks that contain the specified keyword in their description.
     *
     * @param keyword the keyword to search for
     * @return a list of tasks that contain the keyword
     */
    public List<Task> findTasksByKeyword(String keyword) {
        assert keyword != null && !keyword.isEmpty() : "Search keyword must not be null or empty";
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Returns the list of tasks in the TaskList.
     *
     * @return the list of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}