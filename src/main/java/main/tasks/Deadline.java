package main.tasks;

/**
 * Deadline represents a deadline task and is a subclass of the Task class.
 */
public class Deadline extends Task {

    private String by;

    /**
     * A constructor for Deadline.
     * @param description Description of the task as inputted by the user.
     * @param by Date and/ or time representing the end of the event.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toFileFormat() {
        return "D .. " + super.toFileFormat() + " .. " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDate(this.by) + ")";
    }
}
