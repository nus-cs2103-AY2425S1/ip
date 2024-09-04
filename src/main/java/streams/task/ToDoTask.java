package streams.task;

/**
 * Represents a to-do task in the task list.
 */
public class ToDoTask extends Task {

    /**
     * Constructs a ToDoTask with the given description.
     *
     * @param description The description of the to-do task.
     */
    public ToDoTask(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the to-do task.
     *
     * @return A string representation of the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
