/**
 * Represents a Todo task.
 *
 * @author jordanchan
 */
public class Todo extends Task{
    /**
     * Constructs a Todo task with a given description.
     *
     * @param description The description of the Todo task.
     * @throws EmptyInputException if the description is empty.
     */
    public Todo(String description) throws EmptyInputException {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task.
     *
     * @return The string representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + "[" + this.getStatusIcon() + "] " + this.name;
    }
}
