/**
 * This class represents a "to do" with a description and completion status.
 *
 * <p>This class inherits from the Task class It maintains a description
 * of a to do and whether the task has been completed or not.</p>
 */

public class Todo extends Task{

    /**
     * Constructs a new instance of to do.
     *
     * @param description String description of Task.
     */
    public Todo(String description) {
        super(description);
    }


    /**
     * Returns a string representation of the to do.
     *
     * <p>The string includes the E identifier  , status icon and the description of the to do.</p>
     *
     * @return A string in the format {" "[T]" + [statusIcon] description"}.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
