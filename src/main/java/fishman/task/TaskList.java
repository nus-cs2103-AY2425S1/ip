package fishman.task;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * The list of tasks for fishman bot.
 * This class provides the methods to add, retrieve, and get the
 * size of the task list.
 */
public class TaskList implements Iterable<Task> {
    private ArrayList<Task> tasks;

    /** Constructs a new TaskList with an empty list of tasks. */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a new task to the task list.
     *
     * @param newTask The task.Task object to be added to the list.
     */
    public void addTask(Task newTask) {
        assert newTask != null : "Task to be added should not be null";
        tasks.add(newTask);
    }

    /**
     * Retrieves a task object from the task list at the specified index.
     *
     * @param taskIndex The index of the task to retrieve.
     * @return The Task object found at the specified index.
     */
    public Task getTask(int taskIndex) {
        assert taskIndex >= 0 && taskIndex < tasks.size() : "Task index to be retrieved should be of valid range";
        return tasks.get(taskIndex);
    }

    /**
     * Marks a task at the specified index in the task list as done.
     * @param taskIndex The index of the task to retrieve.
     */
    public void markTaskAsDone(int taskIndex) {
        assert taskIndex >= 0 && taskIndex < tasks.size() : "Task index to be marked should be of valid range";
        tasks.get(taskIndex).markAsDone();
    }

    /**
     * Marks a task at the specified index in the task list as not done.
     * @param taskIndex The index of the task to retrieve.
     */
    public void markTaskAsNotDone(int taskIndex) {
        assert taskIndex >= 0 && taskIndex < tasks.size() : "Task index to be unmarked should be of valid range";
        tasks.get(taskIndex).markAsNotDone();
    }

    /**
     * Deletes a task at the specified index in the task list.
     *
     * @param taskIndex The index of the task to delete.
     */
    public void deleteTask(int taskIndex) {
        assert taskIndex >= 0 && taskIndex < tasks.size() : "Task index to be deleted should be of valid range";
        tasks.remove(taskIndex);
    }

    /**
     * Returns the number of tasks currently in the task list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    public boolean isTaskListEmpty() {
        return tasks.isEmpty();
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }
    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }
}
