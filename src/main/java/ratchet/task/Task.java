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
     * @param description The description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for an old task.
     *
     * @param description The description of the task
     * @param isDone The status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

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

    public String toSave() {
        return description + "|" + isDone;
    }
}
