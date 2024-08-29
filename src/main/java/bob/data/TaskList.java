package bob.data;

import bob.tasks.Task;

import java.util.ArrayList;

/**
 * A list of tasks.
 */

public class TaskList extends ArrayList<Task> {
    /**
     * Constructs a new task list with the given tasks.
     *
     * @param taskList the list of tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        super(taskList);
    }

    /**
     * Constructs an empty task list.
     */
    public TaskList() {
        super();
    }
}
