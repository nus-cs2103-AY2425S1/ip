package tecna.task;

/**
 * Represents a task entered by the user.
 * A <code>taskName</code> is the description of the task.
 * An <code>isDone</code> attribute showing the status of the task.
 *
 * @author DennieDan.
 */
public abstract class Task {
    private String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Returns the description of the task
     * which is set as private.
     * @return name of the task.
     */
    public String getTaskName() {
        return this.taskName;
    }

    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns an icon representing the status of the task
     * a cross "X" for DONE and "empty" " " for UNDONE.
     * @return a status icon.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Marks the task as done by setting the
     * isDone attribute to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone by setting the
     * isDone attribute to false.
     */
    public void unMarkAsDone() {
        this.isDone = false;
    }

    /**
     * Returns a string describing the task.
     * @return a string containing the task's information.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.taskName;
    }
}
