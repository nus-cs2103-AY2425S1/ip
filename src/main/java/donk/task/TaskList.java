package donk.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> tasks;

    /**
     * Constructor for the TaskList class that initializes the task list with
     * the provided list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList (List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Default constructor for the TaskList class that initializes an empty task list.
     */
    public TaskList () {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The Task object at the specified index.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Removes the task at the specified index from the task list.
     *
     * @param index The index of the task to remove.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */

    public void remove(int index) {
        this.tasks.remove(index);
    }

    /**
     * Adds a new task to the task list.
     *
     * @param t The Task object to be added to the list.
     */
    public void add(Task t) {
        tasks.add(t);
    }
}
