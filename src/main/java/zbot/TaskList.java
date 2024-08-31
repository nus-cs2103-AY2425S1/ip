package zbot;

import java.util.ArrayList;
import java.util.Iterator;

import zbot.task.Task;

/**
 * Represents a list of tasks.
 */
public class TaskList implements Iterable<Task> {
    private ArrayList<Task> list;

    /**
     * Constructor for TaskList.
     *
     * Creates an empty list of tasks.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Constructor for TaskList.
     *
     * Creates a list of tasks with the given list.
     *
     * @param list List of tasks.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to be added.
     * @return True if the task is added successfully, false otherwise.
     */
    public boolean add(Task task) {
        return this.list.add(task);
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index Index of the task.
     * @return Task at the specified index.
     */
    public Task get(int index) {
        return this.list.get(index);
    }

    /**
     * Removes the task at the specified index.
     *
     * @param index Index of the task.
     * @return Task that is removed.
     */
    public Task remove(int index) {
        return this.list.remove(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Number of tasks in the list.
     */
    public int size() {
        return this.list.size();
    }

    @Override
    public Iterator<Task> iterator() {
        return this.list.iterator();
    }

    @Override
    public String toString() {
        return this.list.toString();
    }
}
