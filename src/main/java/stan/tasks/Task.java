package stan.tasks;

/**
 * Represents a task with a description and a status of whether it is done.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status of the task.
     *
     * @return "X" if the task is done, otherwise " ".
     */
    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone(){
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone(){
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Converts the task to a storage format.
     *
     * @return The string representation of the task in storage format.
     */
    public abstract String toStorageString();

    @Override
    public String toString() {
        return "[" + getStatus() + "] " + description;
    }
}
