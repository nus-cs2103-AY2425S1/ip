package bob.task;

import java.time.LocalDate;

/**
 * Represents an abstract task with a description and completion status to
 * provide the foundational structure for the different types of tasks.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Returns a symbol that indicates the type of task.
     *
     * @return A string symbol representing the task type.
     */
    abstract String getSymbol();

    /**
     * Returns the string representation of the task  with the format for storage.
     *
     * @return A string representation of the task to be saved.
     */
    public abstract String getTaskLine();

    /**
     * Checks if the task occurs on a specified date.
     *
     * @param date The specific date to check relevance against.
     * @return true if the task occurs on the specified date, false otherwise.
     */
    public abstract boolean isRelevant(LocalDate date);

    /**
     * Constructs a new Task with a given description and completion status.
     *
     * @param description The description of the task.
     * @param isDone      The completion status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns task description.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns icon that indicates completion status of the task.
     *
     * @return The completion status icon of the task.
     */
    String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Checks if the task has been completed.
     *
     * @return 1 if task is completed, 0 otherwise.
     */
    int isDoneBinary() {
        return this.isDone ? 1 : 0;
    }

    /**
     * Returns a string representation of the task.
     * Includes the status icon and the task description.
     *
     * @return A formatted string of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
