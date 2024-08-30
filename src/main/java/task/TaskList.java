package task;

import exceptions.BuddyException;
import java.util.ArrayList;

/**
 * Represents a list of tasks.
 * Provides methods to add, delete, mark, unmark tasks, and retrieve the list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param tasks The list of tasks to be included in this TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list by its index.
     *
     * @param index The index of the task to be deleted.
     * @throws BuddyException If the index is out of bounds (i.e., no task exists at the given index).
     */
    public void deleteTask(int index) throws BuddyException {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        } else {
            throw new BuddyException("I don't think that task is on your list, buddy.");
        }
    }

    /**
     * Marks a task as done by its index.
     *
     * @param index The index of the task to be marked as done.
     * @throws BuddyException If the index is out of bounds (i.e., no task exists at the given index).
     */
    public void markTask(int index) throws BuddyException {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsDone();
        } else {
            throw new BuddyException("I don't think that task is on your list, buddy...");
        }
    }

    /**
     * Unmarks a task as undone by its index.
     *
     * @param index The index of the task to be unmarked as undone.
     * @throws BuddyException If the index is out of bounds (i.e., no task exists at the given index).
     */
    public void unmarkTask(int index) throws BuddyException {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsUndone();
        } else {
            throw new BuddyException("I don't think that task is on your list, buddy...");
        }
    }

    /**
     * Checks if a task is marked as done by its index.
     *
     * @param index The index of the task to check.
     * @return true if the task is marked as done, false otherwise.
     */
    public boolean isTaskDone(int index) {
        return tasks.get(index).isDone;
    }

    /**
     * Returns the list of tasks.
     *
     * @return The ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}