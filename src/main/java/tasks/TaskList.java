package tasks;

import java.util.ArrayList;

/**
 * Manages a list of tasks.
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    /**
     * Constructs a new empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a new TaskList with a given list of tasks.
     *
     * @param tasks List of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        assert task != null : "Task to add should not be null";
        tasks.add(task);
    }

    /**
     * Removes a task from the task list.
     *
     * @param index Index of the task to be removed.
     * @return The removed task.
     */
    public Task remove(int index) {
        assert index >= 0 && index < tasks.size() : "Index should be within bounds";
        return tasks.remove(index);
    }

    /**
     * Retrieves a task from the task list.
     *
     * @param index Index of the task to retrieve.
     * @return The retrieved task.
     */
    public Task get(int index) {
        assert index >= 0 && index < tasks.size() : "Index should be within bounds";
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return Number of tasks in the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns all tasks in the task list.
     *
     * @return ArrayList of all tasks.
     */
    public ArrayList<Task> getAll() {
        return tasks;
    }

    /**
     * Finds tasks that contain the specified keyword in their description.
     *
     * @param keyword Keyword to search for.
     * @return TaskList containing tasks that match the keyword.
     */
    public TaskList findTasks(String keyword) {
        assert keyword != null && !keyword.trim().isEmpty() : "Keyword for search should not be null or empty";
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return new TaskList(matchingTasks);
    }
}
