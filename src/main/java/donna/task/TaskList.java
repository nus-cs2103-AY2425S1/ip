package donna.task;

import java.util.ArrayList;
import java.util.List;

import donna.DonnaException;

/**
 * Represents a list of tasks.
 * Contains methods to add, delete, mark, search, and retrieve tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a new, empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the specified list of tasks.
     * Used if data/donna-tasks.txt exists.
     *
     * @param tasks An ArrayList of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        assert tasks != null : "Task list should not be null";
        this.tasks = tasks;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the TaskList.
     *
     * @param taskIdx The index of the task to be deleted.
     * @return The deleted task.
     * @throws DonnaException If the task index is out of range.
     */
    public Task deleteTask(int taskIdx) throws DonnaException {
        assert taskIdx >= 0 && taskIdx < tasks.size() : "Task index is out of bounds";
        if (taskIdx >= 0 && taskIdx < tasks.size()) {
            return tasks.remove(taskIdx);
        } else {
            throw DonnaException.invalidTaskNumber();
        }
    }

    /**
     * Marks a task as done or not done.
     *
     * @param taskIdx The index of the task to be marked.
     * @param isDone  True to mark the task as done, false to mark it as not done.
     * @return The task that was marked.
     * @throws DonnaException If the task index is out of range.
     */
    public Task markTask(int taskIdx, boolean isDone) throws DonnaException {
        assert taskIdx >= 0 && taskIdx < tasks.size() : "Task index is out of bounds";
        if (taskIdx >= 0 && taskIdx < tasks.size()) {
            Task taskToMark = tasks.get(taskIdx);
            if (isDone) {
                taskToMark.markDone();
            } else {
                taskToMark.markNotDone();
            }
            return taskToMark;
        } else {
            throw DonnaException.invalidTaskNumber();
        }
    }

    /**
     * Searches for tasks that contain the specified keyword.
     *
     * @param keyword The keyword to search for.
     * @return A List of tasks that contain the keyword.
     */
    public List<Task> searchTasks(String keyword) {
        List<Task> searchQuery = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.containsKeyword(keyword)) {
                searchQuery.add(task);
            }
        }
        return searchQuery;
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The number of tasks.
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Retrieves a task by its index.
     *
     * @param idx The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task get(int idx) {
        assert idx >= 0 && idx < tasks.size() : "Index is out of bounds";
        return this.tasks.get(idx);
    }

    /**
     * Checks if the TaskList is empty.
     *
     * @return True if the TaskList is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The number of tasks.
     */
    public int size() {
        return this.tasks.size();
    }
}
