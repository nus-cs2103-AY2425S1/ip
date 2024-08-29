package eevee;

/**
 * Represents a type of eevee.Task to be done.
 */
public class Todo extends Task {
    /**
     * Constructs a eevee.Todo task using the given description.
     *
     * @param description The String description of the eevee.Task.
     */
    public Todo(String description) {
        super(description.trim());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        return "T" + super.toFileString();
    }
}
