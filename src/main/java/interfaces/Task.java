package interfaces;

/**
 * Represents a basic task with a description, completion status, and an optional tag.
 * This class serves as a base for more specific task types.
 */
public class Task {
    /**
     * The description of the task.
     */
    protected String description;

    /**
     * The completion status of the task.
     */
    protected boolean isDone;

    /**
     * An optional tag associated with the task.
     */
    protected String tag;

    /**
     * Constructs a new Task with the given description.
     * The task is initially marked as not done and has no tag.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tag = "";
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, " " otherwise.
     */
    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a string representation of the Task.
     *
     * @return A string in the format "[X] description (#tag)" where X is replaced with " " if the task is not done.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description + " (#" + this.tag + ")";
    }

    /**
     * Sets the tag for the task.
     *
     * @param tag The new tag for the task.
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * Marks the task as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void setUnDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the Task suitable for saving to a file.
     * This method can be overridden by subclasses to provide more specific formatting.
     *
     * @return The task description.
     */
    public String loadString() {
        return this.description;
    }
}