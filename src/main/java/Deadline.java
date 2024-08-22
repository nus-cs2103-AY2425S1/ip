public class Deadline extends Task {
    private final String deadline;

    /**
     * Constructor for Deadline class.
     *
     * @param description Description of the task.
     * @param deadline    Deadline of the task.
     */
    public Deadline(String description, String deadline) {
        super(TaskType.DEADLINE, description);
        this.deadline = deadline;
    }

    /**
     * Returns the string representation of the deadline.
     *
     * @return String representation of the deadline.
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + deadline + ")";
    }
}
