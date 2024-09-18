package hien.task;

/**
 * Represents a todo task with a description.
 * Inherits from the {@code Task} class and adds no additional fields or methods.
 */
public class Todo extends Task {

    /**
     * Constructs a new {@code Todo} task with the specified description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo task.
     * The string includes the status icon and description, prefixed with "[T]".
     *
     * @return A formatted string representing the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}