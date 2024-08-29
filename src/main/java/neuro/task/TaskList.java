package neuro.task;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents a list of <code>Task</code>s.
 */
public class TaskList implements Iterable<Task> {
    ArrayList<Task> taskList;

    /**
     * <code>TaskList</code> constructor.
     * Constructs a <code>TaskList</code> with an ArrayList of Tasks.
     *
     * @param taskList the initial list of tasks
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task the task to be added
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return the size of the task list
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return {@code true} if the task list is empty, {@code false} otherwise
     */
    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    /**
     * Retrieves a task at the specified index.
     *
     * @param index the index of the task to retrieve
     * @return the task at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Removes and returns the task at the specified index.
     *
     * @param index the index of the task to remove
     * @return the task that was removed
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public Task removeTask(int index) {
        return taskList.remove(index);
    }

    /**
     * Returns an iterator over the tasks in the list.
     *
     * @return an iterator over the tasks in the list
     */
    @Override
    public Iterator<Task> iterator() {
        return taskList.iterator();
    }
}