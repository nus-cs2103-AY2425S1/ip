package GPT;
/**
 * Represents an abstract task in the GPT application.
 * This class provides common functionality for tasks, such as tracking the task's description,
 * completion status, and type. Subclasses of Task will provide more specific behavior.
 */
abstract class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;

    /**
     * Constructs a Task with the specified description and type.
     * The task is initially marked as not done.
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
     * Returns the status icon of the task, indicating whether it is done.
     *
     * @return "X" if the task is done, otherwise a space " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns the type of the task.
     *
     * @return The TaskType of the task.
     */
    public TaskType getType() {
        return type;
    }

    /**
     * Returns a string representation of the task suitable for saving to a file.
     *
     * @return A formatted string representing the task, including its type, status, and description.
     */
    public String toFileFormat() {
        return String.format("%s | %d | %s",
                this.getClass().getSimpleName().charAt(0),
                isDone ? 1 : 0,
                description);
    }

    /**
     * Returns a string representation of the task, including its type, status, and description.
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        return "[" + type.name().charAt(0) + "][" + getStatusIcon() + "] " + description;
    }
}
