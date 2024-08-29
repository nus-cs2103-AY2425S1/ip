package bob.tasks;

import java.time.LocalDate;

/**
 * Represents a task.
 * There are three types of tasks: Todo, Deadline, and Event.
 */
public abstract class Task {
    protected String description;
    public boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon for the task.
     *
     * @return The status icon as an X or space.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getType() {
        return "task";
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

