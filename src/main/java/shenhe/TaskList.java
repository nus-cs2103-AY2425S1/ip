package shenhe;

import java.util.ArrayList;
import java.util.List;

import shenhe.task.Task;

/**
 * Represents a list of tasks.
 * <p>
 * The {@code TaskList} class manages a collection of {@code Task} objects.
 * It provides methods to add, remove, and retrieve tasks, as well as to get
 * the size of the list.
 * </p>
 */
public final class TaskList {
    private final List<Task> tasks;

    /**
     * Constructs an empty {@code TaskList}.
     * <p>
     * Initializes an empty list of tasks.
     * </p>
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a {@code TaskList} with the specified list of tasks.
     * <p>
     * Initializes the {@code TaskList} with the provided list of tasks.
     * </p>
     *
     * @param tasks A list of {@code Task} objects to initialize the {@code TaskList}.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Retrieves the task at the specified index.
     * <p>
     * Returns the {@code Task} object at the given index in the list.
     * </p>
     *
     * @param taskNumber The index of the task to retrieve.
     * @return The {@code Task} object at the specified index.
     */
    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber);
    }

    /**
     * Adds a task to the list.
     * <p>
     * Appends the given {@code Task} object to the end of the list.
     * </p>
     *
     * @param task The {@code Task} object to add to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes the task at the specified index.
     * <p>
     * Removes the {@code Task} object at the given index from the list.
     * </p>
     *
     * @param taskNumber The index of the task to delete.
     */
    public void deleteTask(int taskNumber) {
        tasks.remove(taskNumber);
    }

    /**
     * Returns the number of tasks in the list.
     * <p>
     * Returns the size of the list, which represents the number of tasks it contains.
     * </p>
     *
     * @return The number of tasks in the list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Finds and returns a list of tasks that contain the specified keyword in their descriptions.
     * <p>
     * This method searches through the current list of tasks and checks if each task's description
     * contains the provided keyword. Tasks that match the keyword are added to a list, which is
     * then returned to the caller.
     * </p>
     *
     * @param keyword The keyword to search for within task descriptions.
     * @return A list of tasks that contain the specified keyword in their descriptions. If no tasks
     *         match the keyword, an empty list is returned.
     */
    public List<Task> findTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        String normalizedKeyword = keyword.trim().toLowerCase(); // Normalize keyword

        for (Task task : tasks) {
            String description = task.toString().toLowerCase().trim(); // Normalize task description
            if (description.contains(normalizedKeyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
