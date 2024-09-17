package utilities;

import tasks.Task;

import java.util.ArrayList;

/**
 * The TaskList class represents a collection of tasks.
 * It extends the ArrayList to provide custom methods for managing tasks.
 */
public class TaskList extends ArrayList<Task> {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }
}
