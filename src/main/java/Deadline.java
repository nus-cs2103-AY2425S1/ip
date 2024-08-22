/**
 * The Deadline class represents a task with a specific deadline.
 */
public class Deadline extends Task {
    protected String deadline;

    /**
     * Constructs a new Deadline task with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param deadline The deadline by which the task needs to be completed.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    String getType() {
        return "[D]";
    }

    /**
     * Returns the string representation of the Deadline task
     *
     * @return A string representing the Deadline task
     */
    @Override
    public String toString() {
        return this.getType() + super.toString() + " (by: " + this.deadline + ")";
    }
}
