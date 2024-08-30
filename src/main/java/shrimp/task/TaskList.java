package shrimp.task;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents a list of tasks.
 * The {@code TaskList} class provides methods to manage and manipulate tasks in the list.
 */
public class TaskList implements Iterable<Task> {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Retrieves a task from the list by its index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the index of the specified task in the list.
     *
     * @param task The task whose index is to be found.
     * @return The index of the specified task, or -1 if the task is not found.
     */
    public int getIndex(Task task) {
        return tasks.indexOf(task);
    }

    /**
     * Replaces the task at the specified index with a new task.
     *
     * @param index   The index of the task to replace.
     * @param newTask The new task to replace the old task.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public void replaceTask(int index, Task newTask) {
        tasks.set(index, newTask);
    }

    /**
     * Deletes the task at the specified index from the list.
     *
     * @param index The index of the task to delete.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int getCount() {
        return tasks.size();
    }

    /**
     * Returns an iterator over the tasks in the list.
     *
     * @return An {@code Iterator} over the tasks in the list.
     */
    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }

}
