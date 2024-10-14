package tasks;

/**
 * Class that represents a {@code Todo} task.
 * A {@code Todo} task has no date or time specified.
 */
public class Todo extends Task {

    /**
     * Constructs a {@code Todo} task.
     * @param description Description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the task icon, followed by its done/undone status.
     */
    @Override
    public String getStatusIcon() {
        return "[T]" + super.getStatusIcon();
    }
}
