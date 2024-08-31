package TrackBot.task;

/**
 * An abstract class for all the tasks in TrackBot.
 */
public abstract class Task {
    protected String desc;
    protected boolean isDone;

    /**
     * Abstract method to return the string representation of the task for storage.
     *
     * @return A formatted string for storage.
     */
    public abstract String toStorageFormat();

    /**
     * Constructs a Task with the specified description.
     *
     * @param desc The description of the task.
     */
    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * Returns a string representation of the task's status icon.
     *
     * @return "X" if the task is done, otherwise a space.
     */
    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + desc;
    }
}
