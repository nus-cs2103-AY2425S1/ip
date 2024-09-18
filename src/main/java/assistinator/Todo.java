package assistinator;

/**
 * Represents todo task.
 */
public class Todo extends Task {

    /**
     * Initialises a todo task.
     * @param description Task description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * {@inheritDoc}
     */
    public String toFileString() {
        return String.format("T | %s | %s", isDone ? TaskStatus.DONE : TaskStatus.NOTDONE, description);
    }
}
