package sage.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a collection of tasks in the task management application
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs an empty task list
     * Initialises the tasks list to an empty ArrayList
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a task list with an initial list of tasks
     *
     * @param tasks A List of Task objects
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Add a task to the task list
     *
     * @param task The task object to be added to the list
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the task list based on its index
     *
     * @param index The index of the task to be removed
     * @return The Task object that was removed from the list
     */
    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns a list of all tasks in the task list
     *
     * @return A List of all Task objects in the task list
     */
    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    /**
     * Searches for tasks containing the specified keyword in the description
     *
     * @param keyword The keyword to search for
     * @return A list of tasks that contain the keyword
     */
    public List<Task> searchTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.toString().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
