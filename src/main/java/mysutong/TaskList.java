package mysutong;

import java.util.List;
import java.util.ArrayList;

/**
 * Manages a list of tasks in the MySutong application.
 * This class handles operations such as adding, removing, and retrieving tasks.
 */
public class TaskList {
    private final List<Task> tasks; // The list of tasks, stored as a generic List interface.

    /**
     * Constructs a new TaskList with the specified list of tasks.
     * This constructor allows for dependency injection, enabling the use of different list implementations.
     *
     * @param tasks a List of {@link Task} objects to be managed by this TaskList.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Default constructor which initializes the TaskList with an empty ArrayList of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the list of all tasks.
     *
     * @return the list of tasks as an unmodifiable view to prevent external modifications.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Retrieves a task at the specified index.
     *
     * @param index the index of the task to retrieve.
     * @return the task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task the {@link Task} to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the list at the specified index.
     *
     * @param index the index of the task to be removed.
     */
    public void removeTask(int index) {
        tasks.remove(index);
    }
}
