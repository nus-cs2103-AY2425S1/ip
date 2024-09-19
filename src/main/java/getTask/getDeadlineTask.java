package getTask;

import exception.InvalidDeadlineException;
import task.DeadlinesTask;
import task.Task;
import task.ToDoTask;

/**
 * The getDeadlineTask class extends getGeneralTask to represent a specific type of task with a deadline.
 * It is used for creating DeadlineTask objects by parsing tasks from the file.
 */
public class getDeadlineTask extends getGeneralTask {
    String deadline;
    public getDeadlineTask(String c, boolean status, String deadline) {
        super(c, status);
        this.deadline = deadline;
    }

    /**
     * Creates a getDeadlineTask object from task description, status and deadline.
     * It also includes the progress of the task by looking at the status icon.
     * @return Task of deadline task type
     */
    @Override
    public Task getHelperTaskFromFile() {
        try {
            String deadline = this.deadline;
            DeadlinesTask task = new DeadlinesTask(this.des, deadline);
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
