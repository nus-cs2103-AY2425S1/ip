package pikappi.task;

/**
 * Represents a todo task in Pikappi.
 */
public class TodoTask extends Task {

    /**
     * Creates a new TodoTask object.
     *
     * @param description The description of the todo task.
     */
    public TodoTask(String description) {
        super(description, "T");
    }

    /**
     * Creates a new TodoTask object.
     *
     * @param description The description of the todo task.
     * @param isDone The status of the todo task.
     */
    public TodoTask(String description, boolean isDone) {
        super(description, "T", isDone);
    }

    /**
     * Returns the string representation of the todo task.
     *
     * @return The string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
