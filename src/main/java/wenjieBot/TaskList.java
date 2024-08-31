package wenjieBot;

import wenjieBot.tasks.Task;
import java.util.ArrayList;

/**
 * The TaskList class manages a list of tasks in the wenjieBot application.
 * It provides methods to retrieve and manage the list of tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Constructs a TaskList with an initial list of tasks.
     *
     * @param tasks the initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        // No tasks initialized
    }

    /**
     * Retrieves the list of tasks managed by this TaskList.
     *
     * @return an ArrayList of Task objects.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
