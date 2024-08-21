/**
 * Represents a deadline task with a description and a specific deadline.
 * This class extends the Task class and provides additional functionality
 * for deadline tasks.
 */
public class DeadlinesTask extends Task {
    protected String deadline;

    /**
     * Constructs a DeadlinesTask with the specified description and deadline.
     *
     * @param description The description of the deadline task.
     * @param deadline The deadline associated with the task.
     */
    public DeadlinesTask(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Returns a string representation of the deadline task, including its
     * status (done or not), description, and deadline.
     *
     * @return A formatted string representing the deadline task.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(),
                this.deadline);
    }
}
