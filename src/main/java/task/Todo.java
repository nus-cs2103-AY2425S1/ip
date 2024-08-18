package task;

/**
 * The most basic form of Task.
 *
 * @author Toh Yi Hui A0259080A
 */
public class Todo extends Task {
    /**
     * Constructor for a new Todo.
     *
     * @param description the description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Return the String representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
