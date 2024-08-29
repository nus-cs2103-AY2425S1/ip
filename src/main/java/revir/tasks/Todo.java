package revir.tasks;

/**
 * Represents a Todo task.
 * Inherits from the Task class.
 */
public class Todo extends Task {
    public static String format = "todo <description>";

    /**
     * Creates a new Todo object with the given description.
     *
     * @param description the description of the Todo
     */
    public Todo (String description) {
        super(description);
    }
  
    /**
     * Returns a string representation of the Todo object.
     *
     * @return A string representation of the Todo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
