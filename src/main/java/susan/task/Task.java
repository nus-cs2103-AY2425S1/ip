package susan.task;

import java.time.LocalDate;

/**
 * Class representing tasks to be added by user.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        // Mark done task with X
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void undoMark() {
        this.isDone = false;
    }

    public LocalDate getDate() {
        return LocalDate.now().plusDays(7);
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Return String format to be saved to data file.
     */
    public String fileFormat() {
        return " : " + getStatusIcon() + " : " + description;
    }
}
