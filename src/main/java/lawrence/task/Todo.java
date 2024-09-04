package lawrence.task;

/**
 * Represents a simple task with no time constraints.
 */
public class Todo extends Task {
    /**
     * Constructor. Creates an {@link Todo} object with the specified
     * task description.
     * <p>
     * The task will be marked as incomplete by default.
     * </p>
     *
     * @param description the name of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructor. Creates an {@link Todo} object with the specified
     * task description and completion status.
     * <p>
     * The task can be marked as complete or incomplete on creation.
     * </p>
     *
     * @param description the name of the task
     * @param isComplete indicates whether the task is complete
     */
    public Todo(String description, boolean isComplete) {
        super(description, isComplete);
    }

    /**
     * Returns a string representation of the object in a
     * standardised save format.
     *
     * @return a string representation of the object in save format
     */
    public String toSaveFormat() {
        return String.format("T | %s", super.toSaveFormat());
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
