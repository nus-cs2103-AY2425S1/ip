package star.task;

/**
 * Represents a general Task.
 * Contains functions like displaying status, and getting output format.
 */
public class Task {

    protected String description;
    protected String tag;
    protected boolean isDone;

    /**
     * Constructs a general Task with the given description.
     * @param description which is a String that gives a description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a general Task with the given description and tag.
     * @param description which is a String that gives a description of the Task.
     * @param tag String that gives the tag applied to the Task
     */
    public Task(String description, String tag) {
        this.description = description;
        this.isDone = false;
        this.tag = tag;
    }

    /**
     * Gets status icon corresponding to status of task.
     * @return a String of the icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markUndone() {
        this.isDone = false;
    }

    public boolean hasTag() {
        return tag != null;
    }

    /**
     * Gets the tag of the task, given there is one.
     * @return a String of the tag given to the task.
     */
    public String getTag() {
        return this.tag;
    }

    /**
     * Gets the name of the task.
     * @return a String of the description input, which is the name of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the output string of task to be saved in computer's hard drive.
     * @return a String of the output.
     */
    public String getOutput() {
        return String.format("X | %d | %s", isDone ? 1 : 0, description);
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + getDescription();
    }
}
