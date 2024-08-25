/**
 * Deadline class includes the name of the task and a deadline for the task to be completed by.
 * Subclass of Task class.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructor for Deadline instance.
     *
     * @param description Name of deadline task.
     * @param by Deadline for task.
     */
    public Deadline(String description, String by) {
        super(description, TaskType.Deadline);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Formats Deadline for saving.
     *
     * @return String Formatted details of Deadline.
     */
    @Override
    public String saveDetails() {
        return "D | " + super.saveDetails() + " | " + by;
    }
}
