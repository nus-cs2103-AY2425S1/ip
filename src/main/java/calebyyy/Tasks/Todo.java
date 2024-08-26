package calebyyy.Tasks;

/**
 * Represents a todo task.
 */
public class Todo extends Task {
    /**
     * Constructor for Todo.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the todo task.
     *
     * @return The string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation of the todo task in save format.
     *
     * @return The string representation of the todo task in save format.
     */
    @Override
    public String toSaveFormat() {
        return "T" + super.toSaveFormat();
    }
}