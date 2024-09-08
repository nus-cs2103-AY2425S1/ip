package patrick.tasklist;

/**
 * The {@code Task} class represents a generic task with a description and a completion status.
 * It provides methods to mark the task as done or undone and to retrieve its status and description.
 */
public class Task extends TaskList {
    public static final String COLUMN = " | ";
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a {@code Task} with the specified description.
     * The task is initialized as not done.
     *
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task, which indicates whether it is done or not.
     *
     * @return "X" if the task is done, "O" if the task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : "O");
    }

    /**
     * Marks the task as done by setting the {@code isDone} flag to {@code true}.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone by setting the {@code isDone} flag to {@code false}.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return the description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a string representation of the {@code Task}, including its status icon and description.
     *
     * @return a formatted string representing the {@code Task}.
     */
    @Override
    public String toString() {
        return getStatusIcon() + COLUMN + this.description;
    }
}
