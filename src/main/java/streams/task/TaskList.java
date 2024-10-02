
package streams.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import streams.exception.StreamsException;
/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        assert tasks != null : "Initial task list cannot be null";
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        assert task != null : "Cannot add null task";
        tasks.add(task);
        assert tasks.contains(task) : "Task should be in the list after adding";
    }

    /**
     * Deletes a task from the list.
     *
     * @param index The index of the task to be deleted.
     */
    public void deleteTask(int index) {
        assert index >= 0 : "Task index cannot be negative";
        assert index < tasks.size() : "Task index out of bounds";
        tasks.remove(index);
    }

    /**
     * Gets a task from the list.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     */
    public Task getTask(int index) throws StreamsException {
        try {
            assert index >= 0 : "Task index cannot be negative";
            System.out.println(tasks.size());
            assert index < tasks.size() : "Task index out of bounds";
        } catch (AssertionError e) {
            throw new StreamsException(e.getMessage());
        }
        return tasks.get(index);
    }

    /**
     * Finds tasks that contain the given keyword in their description.
     *
     * @param keyword The keyword to search for.
     * @return A list of tasks that contain the keyword.
     */
    public List<Task> findTasks(String keyword) {
        assert keyword != null : "Search keyword cannot be null";
        return tasks.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toList());
    }

    /**
     * Gets all tasks in the list.
     *
     * @return A copy of the list of all tasks.
     */
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks);
    }


    /**
     * Gets the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return true if the task list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
