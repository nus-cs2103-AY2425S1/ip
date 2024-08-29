package dgpt.task;

/**
 *  Represents a task that has to be completed. A {@code dgpt.task.Task} corresponds to a description represented by a string,
 *  as well as a completion status.
 */
abstract public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Creates a new dgpt.task.Task Instance.
     *
     * @param description the description of the task created.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the String representation of the task's completion status.
     *
     * @return the icon of the task's completion status
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}