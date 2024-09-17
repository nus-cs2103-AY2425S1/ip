package getTask;

import task.Task;

public abstract class getGeneralTask {

    String des;
    boolean status;
    public getGeneralTask(String taskDes, boolean status) {
        this.des = taskDes;
        this.status = status;
    }

    public abstract Task getHelperTaskFromFile();
}
