package shenhe;

import shenhe.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks.
 * <p>
 * The {@code TaskList} class manages a collection of {@code Task} objects.
 * It provides methods to add, remove, and retrieve tasks, as well as to get
 * the size of the list.
 * </p>
 */
public class TaskList {
    private List<Task> tasks;

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

    public List<Task> findTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.toString().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
