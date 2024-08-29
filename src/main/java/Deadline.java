public class Deadline extends Task {

    // deadline of Deadline task
    String by;

    /**
     * Constructor for Deadline task
     * @param description description of task
     * @param by deadline for task
     */
    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);

        this.by = by;
    }

    /**
     * Returns string representation of Deadline for file writing
     *
     * @return String formatted by Task including deadline
     */
    @Override
    public String getSaveFormat() {
        return super.getSaveFormat() + " | " + by;
    }

    /**
     * Returns String representation of Deadline
     * @return "[D]" with string representation of Task and included deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
