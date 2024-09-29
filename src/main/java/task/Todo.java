package task;

import java.time.LocalDate;

/**
 * A todo task.
 *
 * @author dwsc37
 */
public class Todo extends Task {
    /**
     * Constructor for a <code>Todo</code>.
     *
     * @param description Description of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructor for a <code>Todo</code>, with <code>isDone</code> included.
     *
     * @param description Description of the todo.
     * @param isDone Completion status of the todo.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toData() {
        return "T | " + super.toData();
    }

    @Override
    protected boolean isOnDate(LocalDate date) {
        return false; // todos do not have a date associated with them, so always return false
    }
}
