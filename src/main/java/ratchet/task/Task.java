package ratchet.task;

/**
 * Abstract class representing a task.
 */
public abstract class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructor for a new task.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for an old task.
     *
     * @param description The description of the task.
     * @param isDone      The status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
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
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns true if description contains keyword.
     *
     * @param keyword The keyword of the task to be found.
     * @return True if description contains keyword.
     */
    public boolean isMatch(String keyword) {
        return description.contains(keyword);
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done ratchet.task with X
    }

    @Override
    public String toString() {
        String check = "[" + getStatusIcon() + "]";
        return check + " " + description;
    }

    /**
     * Returns the string representation of task in savable format.
     *
     * @return The string representation of task in savable format.
     */
    public String toSave() {
        return description + "|" + isDone;
    }
}
