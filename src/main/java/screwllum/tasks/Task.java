package screwllum.tasks;

/**
 * Represents an abstract task with a description and a status indicating whether it is done.
 * Provides common functionality for all types of tasks.
 */
public abstract class Task {
    protected String desc;
    protected boolean isDone;
    
    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    /**
     * Toggles the status of the task between done and not done.
     */
    public void toggleStatus() {
        isDone = isDone ? false : true;
    }

    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatus(), desc);
    }

    /**
     * Converts the task to a format suitable for saving to a file.
     * This method must be implemented by subclasses.
     *
     * @return A string representation of the task in a format for saving.
     */
    public abstract String toSaveFormat();
}
