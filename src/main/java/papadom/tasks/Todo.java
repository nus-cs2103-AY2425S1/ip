package papadom.tasks;
/**
 * Represents a to-do task.
 * A to-do task only contains a description and does not include any date or time information.
 */
public class Todo extends Task {
    /**
     * Constructs a to-do task with the specified description.
     *
     * @param description The description of the to-do task.
     */
    public Todo(String description) {
        super(description);
    }
    /**
     * Returns a string representation of the to-do task.
     *
     * @return A string representing the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
