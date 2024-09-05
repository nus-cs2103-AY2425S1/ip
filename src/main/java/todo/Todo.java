package todo;
import task.Task;




/**
 * Represents a Todo task.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo with the specified description and input.
     *
     * @param description The description of the todo task.
     * @param input The input used to create the todo task.
     */
    public Todo (String description, String input) {
        super(description, input);
    }

    /**
     * Returns a string representation of the todo task.
     *
     * @return The string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}