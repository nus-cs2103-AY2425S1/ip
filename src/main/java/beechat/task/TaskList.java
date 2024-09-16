package beechat.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks in the Beechat chatbot application.
 * Provides methods to modify the list.
 */
public class TaskList {

    /**
     * The list of tasks.
     */
    private final List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param tasks The specified list of tasks to be initialized in the TaskList.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The specified task to be added to the TaskList.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task at the specified index from the TaskList.
     *
     * @param index The 1-based index of the specified task to be removed from the TaskList.
     * @return The task that is removed.
     */
    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Gets a task at the specified index from the TaskList.
     *
     * @param index The 1-based index of the specified task to be returned from the TaskList.
     * @return The task that is returned.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Gets the number of tasks in the TaskList.
     *
     * @return The number of tasks in the TaskList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks if the TaskList is empty.
     *
     * @return (@code true) if the TaskList is empty (@code false) otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Gets all the tasks from the TaskList and returns them in a list.
     *
     * @return The list of tasks that is returned.
     */
    public List<Task> getTasks() {
        return tasks;
    }
}
