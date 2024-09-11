package justbot.task;

import java.util.ArrayList;
import java.util.Iterator;

import justbot.exception.JustbotException;

/**
 * Represents a list of tasks.
 * Implements {@link Iterable} to allow iteration over tasks.
 */
public class TaskList implements Iterable<Task> {
    private ArrayList<Task> tasks;

    /**
     * Constructs a new TaskList with the given list of tasks.
     *
     * @param tasks The list of tasks to initialise the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns an iterator over the tasks in this list.
     *
     * @return An iterator over the tasks.
     */
    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }

    /**
     * Returns the number of tasks in this list.
     *
     * @return The number of tasks.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns the task at the specified position in this list.
     *
     * @param index Index of the task to return (0-based).
     * @return The task at the specified position in this list.
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Appends the specified task to the end of this list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes the task at the specified position in this list.
     *
     * @param deleteNumber The 1-based index of the task to be removed.
     */
    public void delete(int deleteNumber) {
        tasks.remove(deleteNumber - 1);
    }

    /**
     * Returns true if this list contains no tasks.
     *
     * @return {@code true} if this list contains no tasks.
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Validates that the task list is not empty.
     *
     * @throws JustbotException if the task list is empty.
     */
    public void validateNotEmpty() throws JustbotException {
        if (isEmpty()) {
            throw new JustbotException("Hey man you have no tasks in your list!");
        }
    }

    /**
     * Validates the task number for marking a task as done.
     *
     * @param taskNumber The 1-based index of the task to be marked as done.
     * @throws JustbotException if the task number is invalid or the task is already marked.
     */
    public void validateMarkTaskNumber(int taskNumber) throws JustbotException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new JustbotException("Hey man there is no such task!");
        }
        if (tasks.get(taskNumber - 1).getIsDone()) {
            throw new JustbotException("Hey man this task is already marked!");
        }
    }

    /**
     * Validates the task number for unmarking a task.
     *
     * @param taskNumber The 1-based index of the task to be unmarked.
     * @throws JustbotException if the task number is invalid or the task is already unmarked.
     */
    public void validateUnmarkTaskNumber(int taskNumber) throws JustbotException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new JustbotException("Hey man there is no such task!");
        }
        if (!tasks.get(taskNumber - 1).getIsDone()) {
            throw new JustbotException("Hey man this task is already unmarked!");
        }
    }

    /**
     * Validates the task number for deleting a task.
     *
     * @param taskNumber The 1-based index of the task to be deleted.
     * @throws JustbotException if the task number is invalid.
     */
    public void validateDeleteTaskNumber(int taskNumber) throws JustbotException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new JustbotException("Hey man there is no such task!");
        }
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

}
