package yapyap;

/**
 * Represents a Todo task with a description.
 * A Todo task can be marked as done or not done.
 */
public class Todo extends Task {

    /**
     * Creates a Todo task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Creates a Todo task with the specified description and completion status.
     *
     * @param description The description of the Todo task.
     * @param isDone The completion status of the Todo task.
     */
    public Todo(String description, Boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of the Todo task, including its status and description.
     *
     * @return A formatted string representing the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
