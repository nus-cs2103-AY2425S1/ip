package bob.tasks;

/**
 * Represents a Todo Task with a string description.
 * Inherits from the Task class, and includes additional details specific
 * to Todo.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo Task with the specified description.
     * @param description the description of the event task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a formatted string representation of the Todo Task to be saved to a file.
     * The format includes the task type, completion status, description.
     */
    @Override
    public String fileFormat() {
        return "T | " + super.fileFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
