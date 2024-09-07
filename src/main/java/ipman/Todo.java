package ipman;

/**
 * Represents a todo task.
 * Extends the Task class to provide a specific type of task.
 * Provides a method to display the todo task details.
 *
 * @author miloaisdino
 */
public class Todo extends Task {
    /**
     * Constructs a Todo task with a specified description.
     *
     * @param description The name or description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
