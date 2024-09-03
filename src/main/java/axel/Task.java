package axel;

/**
 * Represents a task with a description and completion status.
 * This is an abstract class and should be extended by specific task types like {@link ToDoTask}, {@link DeadlineTask}, and {@link EventTask}.
 */
public abstract class Task {
    /** The description of the task. */
    protected String taskName;

    /** Indicates whether the task is completed. */
    protected boolean isDone;

    /**
     * Constructs a {@code Task} with the specified description.
     *
     * @param taskName A description of the task
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Returns a string representation of this task suitable for saving to a file.
     * Subclasses must implement this method to provide the specific file format.
     *
     * @return A string representation of the task in file format
     */
    public abstract String toFileFormat();

    /**
     * Checks if the task is completed.
     *
     * @return {@code true} if the task is completed, {@code false} otherwise
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the {@code Task}.
     * The format is: "[X] description" if the task is done, "[ ] description" if not done.
     *
     * @return A formatted string representation of the task
     */
    @Override
    public String toString() {
        return (this.isDone ? "[X] " : "[ ] ") + this.taskName;
    }
}
