package peppa.data;

import java.util.ArrayList;

import peppa.tasks.Task;

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
