package megamind.task;

import java.io.Serial;

/**
 * The `To do` class represents a to-do task.
 * It extends the `Task` class and provides a specific implementation for to-do tasks.
 * The class includes methods to construct a to-do task and return its string representation.
 */
public class Todo extends Task {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for the to do class.
     *
     * @param description Description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
