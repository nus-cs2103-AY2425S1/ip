/**
 * Represents a generic task with a description and completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType taskType;

    /**
     * Constructs a Task with the specified description and type.
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
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, otherwise " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done or not done.
     *
     * @param status The status to set (true for done, false for not done).
     */
    public void markAsDone(boolean status) {
        this.isDone = status;
    }

    /**
     * Validates the task's description based on the command.
     *
     * @param command The command string used to create the task.
     * @throws OllieException If the description is invalid.
     */
    public abstract void validateDescription(String command) throws OllieException;


    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + "[" + taskType + "] " + description;
    }
}