package matcha.task;

/**
 * Represents a todo task with only a description.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task given a description.
     *
     * @param description Description of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the Todo task when saving to a file.
     *
     * @return String representation of the Todo task when saving to a file.
     */
    @Override
    public String toSaveString() {
        return "T | " + super.toSaveString();
    }

    /**
     * Returns the string representation of the Todo task when printing to the user.
     *
     * @return String representation of the Todo task when printing to the user.
     */
    @Override
    public String toString() {
        return "\t[T]" + super.toString();
    }
}
