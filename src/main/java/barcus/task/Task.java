package barcus.task;

/**
 * Task to be completed
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task without isdone status
     * @param description description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor with isDone
     * @param description of task
     * @param isDone status of task
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns how the status should be represented
     * @return
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks task as done
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Unmarks task as undone
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Converts task into save file friendly string
     * @return save file friendly string
     */
    public String convertToSavedString() {
        String status = this.isDone ? "1" : "0";
        return status + " | " + this.description;
    }

    /**
     * Return string representation
     * @return string representation
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
