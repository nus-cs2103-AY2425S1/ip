package sunny;
/**
 * Represent todo tasks
 */
public class TodoTask extends Task {
    public TodoTask(String description, boolean isDone) {
        super(description, isDone);
    }

    public TodoTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        if (isDone) {
            return String.format("[T][X] %s", description);
        } else {
            return String.format("[T][] %s", description);
        }
    }
}
