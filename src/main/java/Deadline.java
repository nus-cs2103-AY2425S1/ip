/**
 * The {@code Deadline} class represents a task that has a specific deadline.
 * It is a subclass of {@link Task}.
 */
public class Deadline extends Task {

    /** The deadline by which the task needs to be completed. */
    protected String deadline;

    /**
     * Constructs a new {@code Deadline} task with the specified description and deadline.
     * The task type is set to {@link TaskType#DEADLINE}.
     *
     * @param description The description of the deadline task.
     * @param deadline    The deadline by which the task must be completed.
     */
    public Deadline(String description, String deadline) {
        super(description, TaskType.DEADLINE);
        this.deadline = deadline;

    }

    /**
     * Returns a string representation of the deadline task,
     * including its type indicator, description, and deadline.
     *
     * @return A string in the format: "[D][status] description (by: deadline)".
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
