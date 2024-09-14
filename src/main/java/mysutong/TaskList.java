package mysutong;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a list of tasks in the MySutong application. This class provides methods
 * to manage tasks such as adding, removing, and retrieving tasks based on their index.
 * It also supports searching for tasks based on a keyword found in the task's description.
 */
public class TaskList {
    private final List<Task> tasks; // The list of tasks, stored as a generic List interface.

    /**
     * Constructs a new TaskList using an existing list of tasks.
     * This allows for initializing the TaskList with a predefined list,
     * enabling flexibility in the type of List used.
     *
     * @param tasks A List of {@link Task} objects to initialize the TaskList.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Default constructor that initializes the TaskList with an empty ArrayList.
     * This is useful when no initial tasks are provided, and tasks need to be added dynamically.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the list of tasks. The list is returned as an unmodifiable view to prevent
     * external modifications. This encapsulation helps maintain the integrity of the task list.
     *
     * @return An unmodifiable list of {@link Task} objects.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Retrieves a task from the list based on its index. This method ensures that
     * tasks can be accessed directly via their positional index in the list.
     *
     * @param index The index of the task to retrieve.
     * @return The {@link Task} object at the specified index.
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size()).
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Adds a new task to the task list. This method allows tasks to be added to the list,
     * maintaining the order in which they are added.
     *
     * @param task The {@link Task} object to add to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the task list based on its index. This method allows for
     * direct removal of a task from the list, affecting the list size and indices of subsequent tasks.
     *
     * @param index The index of the task to be removed.
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size()).
     */
    public void removeTask(int index) {
        tasks.remove(index);
    }

    /**
     * Searches for tasks that contain the specified keyword in their description.
     * This method performs a case-insensitive search through all tasks in the list,
     * collecting those that contain the keyword in their description.
     *
     * @param keyword The keyword to search for within the task descriptions.
     * @return A list of {@link Task} objects that contain the keyword in their description.
     */
    public List<Task> findTasksByKeyword(String keyword) {
        List<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.description.toLowerCase().contains(keyword.toLowerCase())) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }
}
