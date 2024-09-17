package meowmeow;

import java.util.ArrayList;

/**
 * Represents a list of Task objects
 */
public class TaskList extends ArrayList<Task> {

    private ArrayList<Task> tasks;
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
}