package myapp.task;

/**
 * The {@code Todo} class represents a task with only a description and no specific date/time.
 * It is a subclass of the {@code Task} class and inherits its behavior.
 */
public class Todo extends Task {

    /**
     * Constructs a {@code Todo} task with the specified description.
     *
     * @param description A {@code String} describing the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the {@code Todo} task,
     * prefixed with "[T]" to indicate it is a Todo type.
     *
     * @return A {@code String} representation of the {@code Todo} task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

