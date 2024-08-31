package talkabot.task;

/**
 * Task class contains task description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task class.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns status icon of task.
     *
     * @return Status icon.
     */
    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done talkabot.task with X
    }

    /**
     * Returns string representation of task.
     *
     * @return Task string.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks task as not done.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns string representation of task
     * to be stored in save file.
     *
     * @return Task description.
     */
    public String fileString() {
        return (this.isDone
                ? "1 | "
                : "0 | ")
                + this.description;
    }
}