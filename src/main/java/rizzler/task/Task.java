package rizzler.task;

/**
 * Represents the abstract Task for subclassing.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    protected Task(String description) {
        this(description, false);
    }

    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the task description.
     * @return String describing the task
     */
    public String getDesc() {
        return description;
    }

    /**
     * Tells whether the task has been marked as done.
     * @return boolean on whether the task is done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks a task as done.
     */
    public void done() {
        isDone = true;
    }

    /**
     * Marks a task as undone.
     */
    public void undone() {
        isDone = false;
    }

    /**
     * Formats the printing description of any given task, based on whether it has been done.
     * @return String representing the status of the task: done or undone.
     */
    private String getStatus() {
        if (isDone()) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    /**
     * Returns the type of this particular task in a representative string.
     * @return String representation of the task type.
     */
    public abstract String getType();

    /**
     * Converts the task to a custom tsv format for file storage.
     * @return String properly formatted for writing to file storage.
     */
    public String toTsv() {
        return isDone + "\t" + description;
    };

    /**
     * Formats the entire task for printing out to the user.
     * @return String containing information on task status and description.
     */
    public String toString() {
        return getStatus() + " " + getDesc();
    }

}
