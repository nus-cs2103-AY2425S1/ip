package fishman.task;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * The list of tasks for fishman bot.
 * This class provides the methods to add, retrieve, and get the
 * size of the task list.
 */
public class TaskList implements Iterable<Task> {
    /** The list of tasks stored as an ArrayList. */
    private ArrayList<Task> tasks;

    /** Constructs a new TaskList with an empty list of tasks. */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task The task.Task object to be added to the list.
     */
    public void addTask(Task task) {
        assert task != null : "Task to be added should not be null";
        tasks.add(task);
    }

    /**
     * Retrieves a task object from the task list at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The Task object found at the specified index.
     */
    public Task getTask(int index) {
        assert index >= 0 && index < tasks.size() : "Task index to be retrieved should be of valid range";
        return tasks.get(index);
    }

    /**
     * Marks a task at the specified index in the task list as done.
     * @param index The index of the task to retrieve.
     */
    public void markAsDone(int index) {
        assert index >= 0 && index < tasks.size() : "Task index to be marked should be of valid range";
        tasks.get(index).markAsDone();
    }

    /**
     * Marks a task at the specified index in the task list as not done.
     * @param index The index of the task to retrieve.
     */
    public void markAsNotDone(int index) {
        assert index >= 0 && index < tasks.size() : "Task index to be unmarked should be of valid range";
        tasks.get(index).markAsNotDone();
    }

    /**
     * Deletes a task at the specified index in the task list.
     *
     * @param index The index of the task to delete.
     */
    public void delete(int index) {
        assert index >= 0 && index < tasks.size() : "Task index to be deleted should be of valid range";
        tasks.remove(index);
    }

    /**
     * Returns the number of tasks currently in the task list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }
}
