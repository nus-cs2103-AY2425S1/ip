package duck.tasks;

/**
 * Class representing a "todo" task.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getSaveString() {
        return String.format("T,%s,%s", isDone, description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
