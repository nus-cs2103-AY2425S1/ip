package task;

/**
 * Represents a task with a description and completion status.
 */
public abstract class Task {

    /**
     * Enum representing the type of task.
     */
    public enum TaskType {
        TODO, DEADLINE, EVENT
    }

    public String description;
    public boolean isDone;
    protected final TaskType taskType;

    /**
     * Constructs a Task with the specified description and task type.
     *
     * @param description The description of the task.
     * @param taskType    The type of the task (TODO, DEADLINE, EVENT).
     */
    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    /**
     * Returns a string representation of the task suitable for saving to a file.
     *
     * @return A formatted string representing the task for file storage.
     */
    public String toFileString() {
        return String.format("T | %d | %s", isDone ? 1 : 0, description);
    }

    /**
     * Returns the status icon representing whether the task is done.
     *
     * @return "X" if the task is done, otherwise a blank space.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
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
     * Returns the type of the task as a string.
     *
     * @return A string representing the task type.
     */
    public abstract String getTaskType();
}