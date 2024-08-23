/**
 * Represents a ToDo task with a description.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with description.
     *
     * @param description The description of the ToDo.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of this ToDo task.
     *
     * @return A string representation of this ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}