package getTask;

import task.EventTask;
import task.Task;
import task.ToDoTask;

public class getEventTask extends getGeneralTask {
    String fromTime;
    String toTime;
    public getEventTask(String c, boolean status, String from, String to) {
        super(c, status);
        this.fromTime = from;
        this.toTime = to;
    }

    @Override
    public Task getHelperTaskFromFile() {
        String from = this.fromTime;
        String to = this.toTime;
        EventTask task = new EventTask(this.des, from, to);
        if (this.status) {
            task.markDone();
        }
        return task;
    }
}
