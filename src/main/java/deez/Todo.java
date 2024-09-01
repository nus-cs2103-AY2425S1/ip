package deez;

/**
 * A class representing a Todo task.
 */
public class Todo extends Task {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor for creating a new Todo task with given description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of this Todo task. This includes "[T]" at the beginning.
     *
     * @return A string representing this Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
