package alice;

/**
 * Represents a "Todo" task with a description and a completion status.
 * Inherits from the Task class.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String saveString() {
        return "T" + super.saveString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
