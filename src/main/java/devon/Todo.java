package devon;

/**
 * Represents a "To-Do" task in the Devon application.
 * A "To-Do" task has a description and a status indicating whether it is done or not.
 */
public class Todo extends Task {

    /**
     * Constructs a new "To-Do" task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description A brief description of the "To-Do" task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Provides a string representation of the "To-Do" task in a format suitable for database storage.
     *
     * @return A string representing the "To-Do" task in a format suitable for database storage,
     *         with the format "Todo|&lt;status&gt;|&lt;description&gt;",
     *         where &lt;status&gt; is 1 if done, 0 if not done.
     */
    @Override
    public String dbReadableFormat() {
        return String.format("Todo|%d|%s", this.isDone ? 1 : 0, this.description);
    }

    /**
     * Returns a string representation of the "To-Do" task for display purposes.
     *
     * @return A string representing the "To-Do" task, including its status icon and description,
     *         with the format "[T][status] description", where [status] is "X" if done, " " if not done.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
