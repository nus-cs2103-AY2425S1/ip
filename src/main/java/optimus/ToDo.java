package optimus;

/**
 * Represents a to-do task in the Optimus application.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo task with the specified description.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the to-do task, including its status and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a formatted string for saving the to-do task's data to a file.
     * The string includes the task type, completion status, and description.
     */
    @Override
    public String toSaveString() {
        return "T | " + (isDone() ? "1" : "0") + " | " + getDescription();
    }
}
