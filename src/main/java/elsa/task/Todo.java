package elsa.task;

/**
 * Represents a task that needs to be done.
 * @author Aaron
 */
public class Todo extends Task {
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
