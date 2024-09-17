package getTask;

import task.Task;
import task.ToDoTask;

public class getToDoTask extends getGeneralTask {
    public getToDoTask(String c, boolean status) {
        super(c, status);
    }

    @Override
    public Task getHelperTaskFromFile() {
        ToDoTask task = new ToDoTask(this.des);
        if(this.status) {
            task.markDone();
        }
        return task;
    }
}

