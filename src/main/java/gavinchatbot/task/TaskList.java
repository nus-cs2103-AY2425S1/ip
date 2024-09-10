package gavinchatbot.task;

import java.util.ArrayList;

import gavinchatbot.util.GavinException;

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
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        int initialSize = tasks.size();
        tasks.add(task);
        assert tasks.size() == initialSize + 1 : "TaskList size did not increase after adding a task";
    }

    /**
     * Retrieves a task from the TaskList based on its index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     * @throws GavinException If the index is out of bounds.
     */
    public Task getTask(int index) throws GavinException {
        if (index < 0 || index >= tasks.size()) {
            throw new GavinException("Task number is invalid!!!");
        }
        return tasks.get(index);
    }

    /**
     * Validates if the provided index is within bounds of the TaskList.
     *
     * @param index The index to validate.
     * @throws GavinException If the index is out of bounds.
     */
    private void validateTaskIndex(int index) throws GavinException {
        if (index < 0 || index >= tasks.size()) {
            throw new GavinException("Task number is invalid!!!");
        }
    }

    /**
     * Marks a task in the TaskList as done based on its index.
     *
     * @param index The index of the task to mark as done.
     * @throws GavinException If the index is out of bounds.
     */
    public void markTask(int index) throws GavinException {
        validateTaskIndex(index);
        tasks.get(index).markAsDone();
    }

    /**
     * Unmarks a task in the TaskList as not done based on its index.
     *
     * @param index The index of the task to unmark as not done.
     * @throws GavinException If the index is out of bounds.
     */
    public void unmarkTask(int index) throws GavinException {
        validateTaskIndex(index);
        tasks.get(index).markAsNotDone();
    }

    /**
     * Deletes a task from the TaskList based on its index.
     *
     * @param index The index of the task to delete.
     * @return The task that was deleted.
     * @throws GavinException If the index is out of bounds.
     */
    public Task deleteTask(int index) throws GavinException {
        validateTaskIndex(index);
        int initialSize = tasks.size();
        if (index < 0 || index >= tasks.size()) {
            throw new GavinException("Task number is invalid!");
        }
        assert tasks.size() == initialSize - 1 : "TaskList size did not decrease after deleting a task (Programmer error)";
        return tasks.remove(index);
    }

    /**
     * Returns the list of tasks in the TaskList.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The size of the TaskList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Counts the number of tasks that are marked as done.
     *
     * @return The number of tasks that are done.
     */
    public long countDoneTasks() {
        return tasks.stream().filter(Task::isDone).count();
    }
}
