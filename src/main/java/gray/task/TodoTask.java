package gray.task;

/**
 * Represents a to-do task.
 */
public class TodoTask extends Task {

    public TodoTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
