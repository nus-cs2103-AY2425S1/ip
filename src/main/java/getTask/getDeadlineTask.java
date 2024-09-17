package getTask;

import exception.InvalidDeadlineException;
import task.DeadlinesTask;
import task.Task;
import task.ToDoTask;

public class getDeadlineTask extends getGeneralTask {
    String deadline;
    public getDeadlineTask(String c, boolean status, String deadline) {
        super(c, status);
        this.deadline = deadline;
    }

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
