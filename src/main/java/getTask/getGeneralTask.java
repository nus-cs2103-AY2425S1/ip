package getTask;

import exception.InvalidDeadlineException;
import task.Task;

/**
 * The getGeneralTask class is an abstract class for defining general tasks
 * and serves as the superclass for the tasks to be parsed from the file.
 */
public abstract class getGeneralTask {

    String des;
    boolean status;
    public getGeneralTask(String taskDes, boolean status) {
        this.des = taskDes;
        this.status = status;
    }

    public abstract Task getHelperTaskFromFile();
}
