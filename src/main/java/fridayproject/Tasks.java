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
        // Assertion to ensure that the description is not null and empty
        assert description != null : "Description should not be null";
        assert !description.trim().isEmpty() : "Description should not be empty";
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
        // Assertion to ensure that the task is not marked as done twice
        assert !this.isDone : "Task should not be marked as done twice";
        this.isDone = true;
    }

    /*
     * Marks the task as undone.
     */
    public void markAsUndone() {
        // Assertion to ensure that the task is not marked as undone twice
        assert this.isDone : "Task should not be marked as undone twice";
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
