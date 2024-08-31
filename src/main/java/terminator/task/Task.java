package terminator.task;

/**
 * Abstract base class representing a Task in the application's TaskList.
 */
public abstract class Task {

    /**
     * The description of the task.
     */
    protected String description;

    /**
     * The status of the task, whether it is completed or not.
     */
    protected boolean isDone;

    /**
     * The type of task.
     */
    protected final TaskType taskType;

    /**
     * Creates a new instance of a Task object with the given description and type of task.
     *
     * @param description The description of the task.
     * @param taskType The type of the task.
     */
    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    /**
     * Returns the task type of the current task.
     */
    public TaskType getTaskType() {
        return this.taskType;
    }

    /**
     * Returns the description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the completion status of the task ("X" for complete, " " for incomplete).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the string representation of the task.
     * @example {@code [T][X] destroy aliens}
     */
    @Override
    public String toString() {
        return "[" + this.taskType.toString() + "][" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Marks the current task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the current task as incomplete.
     */
    public void markAsIncomplete() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of the Task to be stored on the local disk.
     * @example {@code "T 1 "}
     */
    public String getDataRepresentation() {
        return this.taskType.toString() + " " + String.valueOf(isDone ? 1 : 0) + " ";
    }
}
