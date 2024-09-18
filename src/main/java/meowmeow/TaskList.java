package meowmeow;

import java.util.ArrayList;

/**
 * Represents a list of Task objects.
 * Extends the functionality of ArrayList to manage tasks.
 */
public class TaskList extends ArrayList<Task> {

    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
}