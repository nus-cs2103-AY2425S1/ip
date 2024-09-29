package pixy.tasks;

/**
 * Represents the abstract class Task.
 */
public abstract class Task {

    /** Description of the task.*/
    protected final String description;

    /** Indicator whether the task is done or not.*/
    protected boolean isDone;

    /**
     * Initialises the description of the task and sets the isDone status to false.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the string representation of the status of the task.
     *
     * @return A string representation of the task status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the description of the task.
     *
     * @return A string representation of the task's description.
     */
    public String getDescription() {
        return this.description; // mark done task with X
    }

    /**
     * Marks the status of the task as done.
     *
     * @param done The status of the task.
     */
    public void markAsDone(boolean done) {
        this.isDone = done;
    }

    /**
     * Returns the status of the task whether it is done or not.
     * @return The value of isDone variable.
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }


}
