/**
 * The {@code Todo} class represents a type of task that only has a description and no specific deadline or time frame.
 * <p>
 * It extends the {@code Task} class and is marked with a "T" to indicate that it is a Todo task.
 * </p>
 */
public class Todo extends Task {

    /**
     * Constructs a new {@code Todo} task with the specified description.
     *
     * @param description The description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the {@code Todo} task.
     * <p>
     * The string includes the task type identifier "[T]" followed by the status icon
     * and description inherited from the {@code Task} class.
     * </p>
     *
     * @return A string representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}