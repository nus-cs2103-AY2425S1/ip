package espresso.task;

/**
 * Represents a task which is a todo task.
 */
public class TodoTask extends Task {
    public TodoTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toText() {
        return "T | " + super.toText();
    }
}