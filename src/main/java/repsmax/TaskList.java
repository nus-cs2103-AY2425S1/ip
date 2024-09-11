package repsmax;

import java.util.List;
import java.util.ArrayList;
import repsmax.Task;
import java.util.stream.Collectors;

/**
 * Represents a list of tasks.
 * <p>
 * The {@code TaskList} class provides methods to manage a collection of
 * {@code Task} objects. It supports adding, retrieving, removing tasks,
 * and querying the size of the task list.
 * </p>
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Constructs an empty {@code TaskList}.
     * <p>
     * Initializes the list to be empty.
     * </p>
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param task the {@code Task} to be added to the list.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Retrieves a task from the list at the specified index.
     *
     * @param index the index of the task to retrieve.
     * @return the {@code Task} at the specified index.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Removes a task from the list at the specified index.
     *
     * @param index the index of the task to remove.
     * @return the {@code Task} that was removed.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the list of tasks.
     * <p>
     * This method returns the underlying list of tasks. Modifications to the
     * returned list will affect the {@code TaskList}.
     * </p>
     *
     * @return the list of {@code Task} objects.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Searches for tasks containing the specified keyword in their description.
     * <p>
     * This method filters the tasks and returns a list of tasks where the
     * description contains the keyword (case-insensitive).
     * It also utilises a a stream to help search for the word.
     * </p>
     *
     * @param keyword the keyword to search for in task descriptions.
     * @return a list of tasks that contain the keyword in their description.
     */
    public List<Task> find(String keyword) {
        return tasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }
}
