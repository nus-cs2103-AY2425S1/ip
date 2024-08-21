public class Deadline extends Task {
    private final String deadline;

    /**
     * Constructor for Deadline class.
     *
     * @param description Description of the task.
     * @param deadline    Deadline of the task.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
