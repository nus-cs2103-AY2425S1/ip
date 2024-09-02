package gale;

/**
 * Represents a to-do task that has no due date or time.
 *
 * @author kaikquah
 */
public class ToDo extends Task {

    /**
     * Constructs a to-do task with the given description.
     * @param description the description of the to-do task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the to-do task as a String.
     * @return the to-do task as a String
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the to-do task as a String to be written to a file.
     * @return the to-do task as a String to be written to a file
     */
    @Override
    public String toFileString() {
        return String.format("T | %d | %s", super.status() ? 1 : 0, getDescription());
    }
}
