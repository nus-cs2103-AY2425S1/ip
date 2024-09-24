package getTask;

import task.Task;
import task.ToDoTask;

/**
 * The getToDoTask class extends getGeneralTask to represent a specific type of task with a description.
 * It is used for creating ToDoTask objects by parsing tasks from the file.
 */
public class getToDoTask extends getGeneralTask {
    public getToDoTask(String c, boolean status) {
        super(c, status);
    }

    /**
     * Creates a getToDoTask object from task description and status.
     * It also includes the progress of the task by looking at the status icon.
     * @return Task of ToDo task type
     */
    @Override
    public Task getHelperTaskFromFile() {
        ToDoTask task = new ToDoTask(this.des);
        if (this.status) {
            task.markDone();
        }
        return task;
    }
}

