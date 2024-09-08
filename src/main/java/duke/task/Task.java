package duke.task;

/**
 * Represents a task with a description, status, and type.
 * This is an abstract class that serves as a base for specific task types like ToDo, Deadline, and Event.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType taskType;

    /**
     * Constructs a Task with the specified description and task type.
     *
     * @param description The description of the task.
     * @param taskType The type of the task, defined by the TaskType enum.
     */
    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    /**
     * Returns the status icon of the task, indicating whether it is done.
     *
     * @return "[X]" if the task is done, "[ ]" otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
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
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the string representation of the task, including its type, status, and description.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return "[" + taskType.name().charAt(0) + "]" + getStatusIcon() + " " + description;
    }

    /**
     * Returns the formatted string representation of the task to be saved in storage.
     * This method must be implemented by subclasses.
     *
     * @return A formatted string for saving the task to a file.
     */
    public abstract String toSaveFormat();

        /**
     * Sets a new description for the task.
     *
     * @param description The new description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}