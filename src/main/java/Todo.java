/**
 * Represents a todo task to be recorded. A <code>Todo</code> object
 * is represented by one String
 * e.g., <code>borrow book</code>
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
