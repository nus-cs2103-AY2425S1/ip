// Deadline class is a task with a deadline, and inherits from Task.
public class Deadline extends Task {
    protected String deadline;

    /**
     * Creates a new Deadline task with the specified description and deadline.
     *
     * @param description the task description
     * @param deadline the deadline by which the task should be completed
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
