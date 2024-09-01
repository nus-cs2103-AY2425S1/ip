package asura.data.tasks;

/**
 * Represents a Todo.
 */
public class Todo extends Task {
    /**
     * Creates a Todo with the specified description.
     * @param description The description of the Todo.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
