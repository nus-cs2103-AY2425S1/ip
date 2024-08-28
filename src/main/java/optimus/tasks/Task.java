package optimus.tasks;

public class Task {
    private boolean isDone;
    private final String taskDesc;

    public Task(String desc) {
        this.isDone = false;
        this.taskDesc = desc;
    }

    /**
     * Returns task description
     * @return
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
     * @return
     */
    public String getStatusString() {
        return this.isDone ? "X" : " ";
    }

    /**
     * Returns a representation of the isDone flag as a string. 1 is true, 0 is false
     * @return
     */
    public int getStatusInt() {
        return this.isDone ? 1 : 0;
    }

    /**
     * Dummy method for child classes to override.
     * TODO: Should be in Storage class
     * TODO: Replace this method with a method returning all relevant storage information as a String[]
     * @return ""
     */
    public String getStorageString() {
        return "";
    }
}
