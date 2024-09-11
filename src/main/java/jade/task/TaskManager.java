package jade.task;

import java.util.ArrayList;
import java.util.List;

import jade.exception.JadeException;
import jade.storage.Storage;

/**
 * Manages a list of tasks.
 */
public class TaskManager {
    private final ArrayList<Task> tasks;
    private final Storage storage;

    /**
     * Constructs a TaskManager object with the specified storage.
     * Loads tasks from the provided storage and initialises the task list.
     *
     * @param storage The storage handler to load and save tasks.
     */
    public TaskManager(Storage storage) {
        this.storage = storage;
        this.tasks = new ArrayList<>(storage.loadTasks());
    }

    /**
     * Returns the number of tasks currently in the list.
     *
     * @return The total number of tasks in the task list.
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Retrieves a task from the task list based on its index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        assert isValidTaskIndex(index) : "Invalid task index: " + index;
        return tasks.get(index);
    }

    /**
     * Finds and returns all tasks that match a given keyword.
     *
     * @param keyword The keyword to search for within task descriptions.
     * @return A list of tasks that match the specified keyword.
     */
    public ArrayList<Task> getMatchingTasks(String keyword) {
        List<Task> matchingTasks = tasks.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .toList();
        return new ArrayList<>(matchingTasks);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        assert storage != null : "Storage should not be null";

        tasks.add(task);
        storage.saveTasks(tasks);
    }

    /**
     * Marks a task from the task list.
     *
     * @param index The index of the task to be marked.
     * @param isDone Whether the task has been marked.
     */
    public void markTask(int index, boolean isDone) {
        assert isValidTaskIndex(index) : "Invalid task index: " + index;
        assert storage != null : "Storage should not be null";

        if (isDone) {
            tasks.get(index).markAsDone();
        } else {
            tasks.get(index).markAsNotDone();
        }
        storage.saveTasks(tasks);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index The index of the task to be removed.
     * @throws JadeException if index is invalid.
     */
    public void deleteTask(int index) throws JadeException {
        assert storage != null : "Storage should not be null";

        if (isValidTaskIndex(index)) {
            tasks.remove(index);
            storage.saveTasks(tasks);
        } else {
            throw new JadeException("Hmm, no such task. Try again.");
        }
    }

    /**
     * Checks if the given index is a valid task index.
     *
     * @param index The index to be checked.
     * @return True if the index is valid, false otherwise.
     */
    public boolean isValidTaskIndex(int index) {
        return index >= 0 && index < tasks.size();
    }
}
