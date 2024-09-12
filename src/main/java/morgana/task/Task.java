package morgana.task;

/**
 * Represents an abstract task with a type and description. A task can be
 * marked as done or not done and converted to a file format for storage.
 */
public abstract class Task {
    private final TaskType type;
    private final String description;
    private boolean isDone = false;

    /**
     * Constructs a {@code Task} with the specified type and description.
     *
     * @param type The type of the task, as defined in {@link TaskType}.
     * @param description The description of the task.
     */
    public Task(TaskType type, String description) {
        this.type = type;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as done or not done.
     *
     * @param isDone {@code true} if the task is done, {@code false} otherwise.
     */
    public void markAsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns a string representing the task in a format suitable for file storage.
     */
    public String toFileFormat() {
        return "%s | %s | %s".formatted(type, getStatusIcon(), description);
    }

    @Override
    public String toString() {
        return "[%s][%s] %s".formatted(type, getStatusIcon(), description);
    }

    private String getStatusIcon() {
        return isDone ? "X" : " ";
    }
}
