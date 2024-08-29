package silverwolf.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The TaskList class represents a collection of tasks.
 * It provides methods to manage and access tasks, including adding, removing, and retrieving tasks.
 */
public class TaskList {
    private final List<Task> tasks; // The list that stores all tasks

    /**
     * Default constructor that initializes an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with an initial list of tasks.
     *
     * @param tasks A List of Task objects to initialize the TaskList.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the TaskList.
     *
     * @param task The Task object to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the TaskList by its index.
     *
     * @param index The index of the task to be removed.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Retrieves the list of tasks in the TaskList.
     *
     * @return A List of Task objects representing all tasks in the TaskList.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The number of tasks currently in the TaskList.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Retrieves a specific task from the TaskList by its index.
     *
     * @param index The index of the task to be retrieved.
     * @return The Task object at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Finds tasks containing the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A list of tasks that contain the keyword.
     */
    public List<Task> findTasks(String keyword) {
        return tasks.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toList());
    }
}
