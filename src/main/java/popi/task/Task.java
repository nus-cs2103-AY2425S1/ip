package popi.task;

import java.time.LocalDateTime;

/**
 * Represents a task.
 */
public abstract class Task {
    private String description;
    private boolean isDone;
    private LocalDateTime time;

    /**
     * Constructor for Task.
     * @param description Description of the task.
     * @param time Time of the task if applicable.
     */
    public Task(String description, LocalDateTime time) {
        this.description = description;
        this.time = time;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    protected void markAsDone() {
        this.isDone = true;
    }

    protected void markAsUndone() {
        this.isDone = false;
    }

    public boolean isDone() {
        return this.isDone;
    }

    protected abstract String getType();

    /**
     * Returns the data string of the task.
     * @return Data string of the task.
     */
    public String toDataString() {
        return getType() + " | " + (isDone ? "1" : "0") + " | " + this.description;
    }
}

