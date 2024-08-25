package moimoi;

import java.util.ArrayList;
import moimoi.task.Task;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructs an encapsulation of the specified list of tasks.
     *
     * @param tasks List of tasks to be encapsulated.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the task of specified index.
     *
     * @return Task of specified index.
     */
    public Task get(int index) {
        return tasks.get(index - 1);
    }

    /**
     * Appends the specified task to the end of the list.
     *
     * @param task Task to be added into the list.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes the task of specified index. Shifts any subsequent tasks leftwards (subtracts one from their indices).
     *
     * @param index Index of task to be deleted.
     */
    public void delete(int index) {
        this.tasks.remove(index - 1);
    }

}
