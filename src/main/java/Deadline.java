import java.io.Serial;

public class Deadline extends Task{
    @Serial
    private static final long serialVersionUID = 1L;
    private final String deadline;

    /**
     * Constructor for the Deadline class.
     *
     * @param description Description of the task.
     * @param deadline Deadline of the task.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Returns the String representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
