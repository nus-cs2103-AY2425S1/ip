package pixel;

/**
 * Represents a Task with a due date
 * A <code>Deadline</code> object corresponds to a Task description and
 * a date and time to complete the Task.
 */
public class Deadline extends Task {
    protected String by; // due date

    /**
     * Constructor method for Deadline
     *
     * @param description description of the task
     * @param by due date of the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns due date of the task
     *
     * @return due date
     */
    public String getBy() {
        return this.by;
    }

    /**
     * Returns the string representation of the Task
     * when user types in the command list
     *
     * @return string representation of Deadline for printing
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns the string representation of the Task
     * that will be saved to file
     *
     * @return string representation of Deadline for saving in file
     */
    @Override
    public String getFileString() {
        return String.format("D | %s | %s | %s", getStatusNumber(), getDescription(), by);
    }
}
