package applemazer;

import tasks.*;
import java.util.ArrayList;

/**
 * Class that abstracts a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructor for the TaskList object which abstracts an ArrayList of tasks.
     * @param tasks The task list to store.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * @param task The task to add to the task list.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * @param task The task to remove from the task list.
     */
    public void remove(Task task) {
        tasks.remove(task);
    }

    /**
     * @param taskNumber The task to get from the task list.
     * @return The task retrieved from the task list.
     */
    public Task get(int taskNumber) {
        return tasks.get(taskNumber);
    }

    /**
     * @return Returns {@code true} if the task list is empty.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * @return Returns the size of the task list stored.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * @return Returns the task list stored.
     */
    public ArrayList<Task> getList() {
        return tasks;
    }
}
