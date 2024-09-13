package ollie.task;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the formatted string for easy parsing and writing into file (database).
     *
     * @return Formatted String.
     */
    public String getFormattedString() {
        return "T | " + super.getFormattedString();
    }
}