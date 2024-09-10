package xbot.task;

/**
 * Represents a generic task in the XBot application.
 * A task has a description, a completion status, and a type.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;

    /**
     * Constructs a new Task with the specified description and type.
     * The task is initially not marked as done.
     *
     * @param description The description of the task.
     * @param type The type of the task, represented by the {@link TaskType} enum.
     */
    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Returns the type of the task.
     *
     * @return The task type.
     */
    public TaskType getType() {
        return type;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status icon for the task.
     * The icon is "X" if the task is done, otherwise a space " ".
     *
     * @return The status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done without any additional output.
     */
    public void setIsDone() {
        isDone = true;
    }

    /**
     * Marks the task as done and prints a confirmation message.
     */
    public String markAsDone() {
        isDone = true;
        return ("Nice! I've marked this task as done:\n"
                + toString());
    }

    /**
     * Marks the task as undone and prints a confirmation message.
     */
    public String markAsUndone() {
        isDone = false;
        return ("OK, I've marked this task as not done yet:\n"
                + toString());
    }

    /**
     * Checks if the task is done.
     * @return {@code true} if the task is done, otherwise {@code false}.
     */
    public boolean isDone() {
        return isDone;
    }
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
