/**
 * Represents a todo task.
 */
public class Todo extends Task {

    /**
     * The constructor.
     *
     * @param description Todo description.
     * @throws BocchiException If the description is empty.
     */
    public Todo(String description) throws BocchiException {
        super(description);
    }

    /**
     * Returns a string representation of this todo.
     *
     * @return A string representation of this todo.
     */
    @Override
    public String toString() {
        return "[TODO]" + super.toString();
    }
}
