/**
 * The Todo class represents a simple task without any specific time constraints.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with the specified description.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the Todo task
     *
     * @return A string representing the Todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
