package bob;

import bob.task.Task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a list of tasks.
 */
public class TaskList implements Iterable<Task> {
    private final List<Task> TASKS;

    /**
     * Constructs an empty task list.
     */
    public TaskList() {
        this.TASKS = new ArrayList<>();
    }

    /**
     * Constructs a task list populated by the given list of tasks.
     *
     * @param tasks the tasks that are in the initial task list
     */
    public TaskList(List<Task> tasks) {
        this.TASKS = tasks;
    }

    /**
     * Checks if this list is empty.
     *
     * @return true if this list is empty, false otherwise
     */
    public boolean isEmpty() {
        return TASKS.isEmpty();
    }

    /**
     * Adds the given task to this list.
     *
     * @param task the task to be added
     */
    public void add(Task task) {
        TASKS.add(task);
    }

    /**
     * Removes the task at the given index in this list.
     *
     * @param index index of the task to be removed
     * @return the removed task
     * @throws IndexOutOfBoundsException if the index is out of range (index &lt; 0 || index &gt;= size())
     */
    public Task remove(int index) {
        return TASKS.remove(index);
    }

    /**
     * Returns the task at the given index in this list.
     *
     * @param index index of the task to return
     * @return the task at the given index in this list
     * @throws IndexOutOfBoundsException if the index is out of range (index &lt; 0 || index &gt;= size())
     */
    public Task get(int index) {
        return TASKS.get(index);
    }

    /**
     * Returns the number of tasks in this list.
     *
     * @return the number of tasks in this list
     */
    public int size() {
        return TASKS.size();
    }

    /**
     * Removes all tasks in this list.
     */
    public void reset() {
        TASKS.clear();
    }

    /**
     * Returns the string representation of each task in this list, prefixed by its index (starting from 1)
     * and followed by a linebreak.
     */
    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();

        int i;
        for (i = 0; i < TASKS.size() - 1; ++i) {
            text.append(i + 1).append(".").append(TASKS.get(i)).append("\n");
        }
        text.append(i + 1).append(".").append(TASKS.get(i));

        return text.toString();
    }

    /**
     * Returns an iterator over the tasks in this list.
     *
     * @return an Iterator
     */
    @Override
    public Iterator<Task> iterator() {
        return TASKS.iterator();
    }
}
