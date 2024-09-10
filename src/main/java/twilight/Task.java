package twilight;

/**
 * Stores all the information pertaining to a task and allows modification to status.
 */
public class Task {
    protected String description;
    protected boolean isDone;
//  false status indicates incomplete item

    /**
     * Creates a Task with status as incomplete.
     *
     * @param description What the task is.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a task.
     *
     * @param description What the task is.
     * @param status Whether task is complete.
     */
    public Task(String description, boolean status) {
        this.description = description;
        this.isDone = status;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }

    /**
     * Returns a string format of the task when storing tasklist in a text file.
     * 1 and 0 indicate the completion of the task with 1 being complete.
     */
    public String toStorageString() {
        if (isDone) {
            return "1," + this.description;
        } else {
            return "0," + this.description;
        }
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setUndone() {
        this.isDone = false;
    }
}
