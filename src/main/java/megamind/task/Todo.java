package megamind.task;

import java.io.Serial;

public class Todo extends Task{
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for the to do class.
     *
     * @param description Description of the task.
     */
    public Todo (String description) {
        super(description);
    }

    /**
     * Returns the String representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
