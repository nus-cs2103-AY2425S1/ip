
/**
 * Represents a todo task.
 * A Todo object corresponds to a task represented by a description.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo class.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
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