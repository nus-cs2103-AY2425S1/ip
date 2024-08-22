public class Deadline extends Task {

    // deadline of Deadline task
    String by;

    /**
     * Constructor for Deadline task
     * @param description description of task
     * @param by deadline for task
     */
    public Deadline(String description, String by) {
        super(description);

        this.by = by;
    }

    /**
     * Returns String representation of Deadline
     * @return "[D]" with string representation of Task and included deadline
     */
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
