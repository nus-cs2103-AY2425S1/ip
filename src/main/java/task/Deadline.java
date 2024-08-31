package task;

/**
 * The Deadline class represents a task with a deadline
 */
public class Deadline extends Task {
    private String by;

    /**
     * Constructor for a Deadline
     * @param description String representing the task desrcription
     * @param by String representing the deadline
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
