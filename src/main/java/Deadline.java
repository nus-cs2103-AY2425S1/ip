public class Deadline extends Task {
    protected String by;

    /**
     * Constructor for Deadline class.
     * @param description the description of the task
     * @param by the deadline of the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the task.
     * @return task description with status and deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (" + this.by + ")";
    }
}
