package Task;

/**
 * Represents a task that has no deadline to be completed by.
 *
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getType() {
        return "Todo";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
