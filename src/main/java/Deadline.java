/**
 * The Deadline class represents a task that has a specific due date.
 * It extends the Task class by adding a due date.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a new Deadline with the given description and due date.
     *
     * @param description The description of the task.
     * @param by The due date of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the deadline, including its status icon, description,
     * and due date.
     *
     * @return A string in the format "[D][statusIcon] description (by: due date)".
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}
