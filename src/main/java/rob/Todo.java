package rob;

/**
 * Represents the a todo task that the user adds.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSaveString() {
        return "[T] | " + getStatusIcon() + " | " + description;
    }
}
