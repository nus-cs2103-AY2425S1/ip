package bob.task;

import java.time.LocalDate;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    abstract String getSymbol();
    public abstract String getTaskLine();
    public abstract boolean isRelevant(LocalDate date);

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    int isDoneBinary() {
        return this.isDone ? 1 : 0;
    }

    /**
     * Returns a string representation of the task.
     * Includes the status icon and the task description.
     *
     * @return Task as a formatted string.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
