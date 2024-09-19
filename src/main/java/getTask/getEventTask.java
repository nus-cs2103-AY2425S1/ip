package getTask;

import exception.InvalidDeadlineException;
import task.EventTask;
import task.Task;
import task.ToDoTask;

/**
 * The getEventTask class extends getGeneralTask to represent a specific type of task with a duration.
 * It is used for creating EventTask objects by parsing tasks from the file.
 */

public class getEventTask extends getGeneralTask {
    String fromTime;
    String toTime;
    public getEventTask(String c, boolean status, String from, String to) {
        super(c, status);
        this.fromTime = from;
        this.toTime = to;
    }

    /**
     * Creates a getEventTask object from task description, status and duration.
     * It also includes the progress of the task by looking at the status icon.
     * @return Task of Event task type
     */
    @Override
    public Task getHelperTaskFromFile() {
        try {
            String from = this.fromTime;
            String to = this.toTime;
            EventTask task = new EventTask(this.des, from, to);
            if (this.status) {
                task.markDone();
            }
            return task;
        } catch (InvalidDeadlineException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
