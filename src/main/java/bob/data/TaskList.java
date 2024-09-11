package bob.data;

import java.util.ArrayList;

import bob.tasks.Task;

/**
 * Class representing a list of tasks.
 */
public class TaskList extends ArrayList<Task> {
    public TaskList(ArrayList<Task> taskList) {
        super(taskList);
    }

    public TaskList() {
        super();
    }

    public TaskList getTaskList() {
        return this;
    }
}
