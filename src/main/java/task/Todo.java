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
     * Constructor for a new Todo with the specified status of the Todo.
     *
     * @param description the description of the task.
     * @param isComplete the status of the task.
     */
    public Todo(String description, boolean isComplete) {
        super(description, isComplete);
    }

    /**
     * Return the String representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
