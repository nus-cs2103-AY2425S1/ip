package optimus.tasks;

import optimus.Storage;
import optimus.exceptions.OptimusExceptions;

/**
 * Tasks to be tracked by the user
 */
public class Task {
    private boolean isDone;
    private String taskDesc;

    /**
     * Constructor assigns task as incomplete
     *
     * @param desc task description
     */
    public Task(String desc) {
        this.isDone = false;
        this.taskDesc = desc;
    }

    /**
     * Returns task description
     *
     * @return description
     */
    public String getTaskDesc() {
        return taskDesc;
    }

    /**
     * Sets isDone flag to true
     */
    public void markAsComplete() {
        this.isDone = true;
    }

    /**
     * Sets isDone flag to false
     */
    public void markAsIncomplete() {
        this.isDone = false;
    }

    /**
     * Returns a representation of the isDone flag as a string. "X" is true, " " is false
     *
     * @return status flag
     */
    public String getStatusString() {
        return this.isDone ? "X" : " ";
    }

    /**
     * Returns a representation of the isDone flag as a string. 1 is true, 0 is false
     *
     * @return boolean integer
     */
    public int getStatusInt() {
        return this.isDone ? 1 : 0;
    }

    public void updateDescription(String desc) {
        this.taskDesc = desc;
    }

    public String getTaskType() {
        return "";
    }

    /**
     * Returns string representation of tasks for UI
     * @return task string
     */
    @Override
    public String toString() {
        String s = "";
        s += "[" + getStatusString() + "] ";
        s += getTaskDesc();
        return s;
    }

    /**
     * Returns string representation of task for easier reading from storage
     * @return task string
     */
    public String getStorageString() {
        String s = "";
        s += Storage.SPECIAL_CHAR;
        s += getStatusInt();
        s += Storage.SPECIAL_CHAR;
        s += getTaskDesc();
        s += Storage.SPECIAL_CHAR;
        return s;
    }

    public void updateFirstDate(String firstDate) throws OptimusExceptions {
    }

    public void updateSecondDate(String firstDate) {
    }
}
