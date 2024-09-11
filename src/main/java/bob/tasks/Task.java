package bob.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Abstract class representing a task in the task list.
 */
public abstract class Task {
    protected String description;
    private boolean isDone;

    /**
     * Constructor for a task.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getType() {
        return "task";
    }

    public LocalDateTime getReminderDate() {
        return null;
    }

    public String getBy() {
        return null;
    }

    public String getFrom() {
        return null;
    }

    public String getTo() {
        return null;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public abstract LocalDate getDate();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

