package sigma.task;

/**
 * Class for a Deadline Task.
 *
 * @author Qiao Yi
 */
public class Deadline extends Task {
    private String deadline;

    /**
     * Constructor for a Deadline Task
     * @param name The name of the Deadline
     * @param status Completion status of the Deadline
     * @param deadline The deadline
     */
    public Deadline(String name, boolean status, String deadline) {
        super(name, status);
        this.deadline = deadline;
    }

    /**
     * Returns the deadline of the Deadline task
     * @return The deadline of the Deadline task
     */
    public String getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
