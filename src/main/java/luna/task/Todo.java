package luna.task;

/**
 * Represents a task to be completed.
 */
public class Todo extends Task {

    /**
     * Creates a task without deadline.
     *
     * @param description Description of task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileFormat() {
        String status = super.isDone ? "1|" : "0|";
        return "T|" + status + super.description;
    }
}
