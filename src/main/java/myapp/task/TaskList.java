package myapp.task;

import myapp.exception.RubyException;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code TaskList} class manages a list of tasks.
 * It provides methods to add, remove, retrieve, and list tasks.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a {@code TaskList} with a specified list of tasks.
     *
     * @param tasks A {@code List} of {@code Task} objects to initialize the task list with.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The {@code Task} object to be added to the list.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes a task from the task list at the specified index.
     *
     * @param index The index of the task to be removed.
     * @throws RubyException If the index is out of bounds (less than 0 or greater than or equal to the list size).
     */
    public void removeTask(int index) throws RubyException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new RubyException("Invalid task number.");
        }
        this.tasks.remove(index);
    }

    /**
     * Retrieves a task from the task list at the specified index.
     *
     * @param index The index of the task to be retrieved.
     * @return The {@code Task} object at the specified index.
     * @throws RubyException If the index is out of bounds (less than 0 or greater than or equal to the list size).
     */
    public Task getTask(int index) throws RubyException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new RubyException("Invalid task number.");
        }
        return this.tasks.get(index);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return An integer representing the size of the task list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns the list of tasks.
     *
     * @return A {@code List} of {@code Task} objects contained in the task list.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns a formatted string representation of all tasks in the task list.
     * Each task is prefixed with its index in the list.
     *
     * @return A {@code String} representing the list of tasks with their indexes.
     */
    public String listTasks() {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < this.tasks.size(); i++) {
            sb.append("     ").append(i + 1).append(".").append(this.tasks.get(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Finds tasks in the list that contain the specified keyword in their description.
     *
     * @param keyword The keyword to search for.
     * @return A {@code String} representing the tasks that match the search criteria.
     */
    public String findTasks(String keyword) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        int taskNumber = 1;
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                sb.append("     ").append(taskNumber++).append(".").append(task).append("\n");
            }
        }
        if (taskNumber == 1) {
            sb.append("     No matching tasks found.");
        }
        return sb.toString();
    }
}