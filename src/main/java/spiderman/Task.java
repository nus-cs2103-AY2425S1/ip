package spiderman;

/**
 * Represents a task with a description and a status indicating whether it is done.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Construct a Task object with the given description.
     * The newly created Task object will be marked as not done.
     * @param description The description of this task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get the description of the task instance.
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Get the status of the task instance.
     * True for task done and false for task not done.
     * @return The task's status.
     */
    public boolean isTaskDone() {
        return this.isDone;
    }

    /**
     * Formats the task's description and status to be saved into a txt file.
     * @return A string that contains the task's description and status.
     */
    public String toTextFormat() {
        return (this.isDone ? "T" : "F") + "|" + this.getDescription();
    }

    /**
     * Gets the status icon of the task.
     * @return "X" for task done or " " for task not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Set the task status to done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Set the task status to not done
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Converts task description and status to a string.
     * @return A string containing the task's description and current status.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
