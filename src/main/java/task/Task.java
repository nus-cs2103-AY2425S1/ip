package task;
import prince.Prince;

/**
 * Represents a task with a description and completion status.
 *
 * The Task class provides methods for managing and retrieving the task details
 * Parent class of the various subtask types
 */

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
     *
     * @param description the description of the task.
     */

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {

        assert this.description != null : "Description should not be null";

        return this.description;
    }
    public void setProgress(boolean b) {
        this.isDone = b;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns a string representation of the task in a human-readable format.
     *
     * @return String
     */
    public String printTask() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public abstract String printFileFormat();

    /**
     * Marks the task as done and returns a string output.
     *
     * @return a confirmation message
     */

    public String markDone() {
        this.setProgress(true);
        return "Nice! I've marked this task as done:\n  " + this.printTask();
    }

    /**
     * Marks the task as not done and returns a string output.
     *
     * @return a confirmation message
     */

    public String markIncomplete() {
        this.setProgress(false);
        return "OK! I've marked this task as not done yet:\n  " + this.printTask();
    }

}
