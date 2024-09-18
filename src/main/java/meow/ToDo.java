package meow;

import java.io.Serializable;

/**
 * Represents a to-do task.
 * A to-do task is a simple task with a description but no specific date or time.
 */
public class ToDo extends Task implements Serializable {

    protected String by;

    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the ToDo object, including its type and description
     *
     * @return A string that represents the ToDo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
