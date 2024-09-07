package arsenbot.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Construct a Task with the specified description.
     * By default, the task is not done.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the string format of the task to be saved in a file.
     * This method must be implemented by concrete subclassed of Task.
     *
     * @return the file format of the task as a string
     */
    public abstract String toFileFormat();
}