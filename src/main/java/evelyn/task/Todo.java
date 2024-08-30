package evelyn.task;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {
    /**
     * Constructs a new Todo object with the given descriotion and status
     * @param description Description of the Todo
     * @param isMarked Status of the Todo
     */
    public Todo (String description, boolean isMarked) {
        super(description, isMarked);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
