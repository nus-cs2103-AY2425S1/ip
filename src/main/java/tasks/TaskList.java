package tasks;

import java.util.ArrayList;

/**
 * Represents a list of tasks. The TaskList class provides operations to add,
 * delete, and manage tasks within the list.
 */
public class TaskList {
    // Contains the task list e.g., it has operations to add/delete tasks in the list
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
    }

    /**
     * Constructs a TaskList with an initial array of tasks.
     *
     * @param tasks An array of Task objects to initialize the TaskList.
     */
    public TaskList(Task[] tasks) {
        for (Task t : tasks) {
            this.tasks.add(t);
        }
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return An ArrayList containing the tasks in the TaskList.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
