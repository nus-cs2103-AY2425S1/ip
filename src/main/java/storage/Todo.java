package storage;

/**
 * Represents a todo task.
 */
public class Todo extends Task {
    /**
     * Creates a new Todo.
     *
     * @param description The description of the todo.
     * @param isDone Whether the todo is done.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the string representation of the todo.
     *
     * @return The string representation of the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the todo in the file format.
     *
     * @return The todo in the file format.
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
