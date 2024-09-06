package fridayproject;

/*
 * Represents a task.
 * A task has a description and a status.
 * Example: [ ] read book
 */
public abstract class Tasks {
    protected String description;
    protected boolean isDone;

    /*
     * Constructor for a task.
     * @param description The description of the task.
     */
    public Tasks(String description) {
        this.description = description;
        this.isDone = false;
    }

    /*
     * Returns the status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /*
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /*
     * Marks the task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /*
     * Returns the string representation of the task.
     */
    public abstract String getTypeIcon();

    /*
     * Returns the string representation of the task to be saved in the file.
     */
    public abstract String toFileString();

    /*
     * Returns the string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
