package johncena.tasks;

import java.time.LocalDate;
/**
 * Represents a todo task.
 * A tasks.Todo object corresponds to a task represented by a description.
 */
public class Todo extends Task {

    /**
     * Constructor for tasks.Todo class.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
        assert description != null : "Description should not be null";
    }

    /**
     * Checks if the task occurs on the given date.
     *
     * @param date The date to check.
     * @return True if the task occurs on the given date, false otherwise.
     */
    @Override
    public boolean occursOn(LocalDate date) {
        return false;
    }
    /**
     * Returns a string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
