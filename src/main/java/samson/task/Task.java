package samson.task;

/**
 * The <code> Task </code> class represents a general task. It is an abstract class
 * that serves as a base for more specific types of tasks
 * like <code> ToDo </code>, <code> Deadline </code>, <code> Event </code>.
 */
public abstract class Task {
    private String description;
    private boolean isDone;
    protected TaskType type;

    /**
     * Constructs a <code> Task </code> with the specified description and type.
     *
     * @param description The description of the task.
     * @param type        The type of the task (e.g., ToDo, Deadline, Event).
     */
    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Marks the task as completed.
     */
    public void complete() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void notComplete() {
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return A string representing the task's status (e.g., "[X]" for completed, "[ ]" for not completed).
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Checks if the task is completed.
     *
     * @return {@code true} if the task is completed, {@code false} otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a string representation of the task for storage in a file.
     *
     * @return The string representation of the task for storage.
     */
    public abstract String toStorageString();

    /**
     * Returns a string representation of the task for display.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + type.name().charAt(0) + "]" + getStatusIcon() + " " + description;
    }
}
