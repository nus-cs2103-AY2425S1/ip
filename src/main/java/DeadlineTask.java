/**
 * Represents a deadline task.
 */
public class DeadlineTask extends Task {
    private String deadline;

    /**
     * Creates a deadline task with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param deadline The deadline of the task.
     */
    public DeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Creates a deadline task with the specified description, deadline and done status.
     *
     * @param description The description of the task.
     * @param deadline The deadline of the task.
     * @param isDone The done status of the task.
     */
    public DeadlineTask(String description, String deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    /**
     * Returns the type label of the task.
     *
     * @return The type label of the task.
     */
    private String getTypeLabel() {
        return "D";
    }

    /**
     * Returns the deadline label of the task.
     *
     * @return The deadline label of the task.
     */
    private String getDeadlineLabel() {
        return String.format("(by: %s)", this.deadline);
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s %s", this.getTypeLabel(), super.toString(), this.getDeadlineLabel());
    }

    /**
     * Returns the string representation of the task in data format (for saving to file)
     *
     * @return The data string.
     */
    @Override
    public String toDataString() {
        return String.format("%s|%s|%s", this.getTypeLabel(), super.toDataString(), this.deadline);
    }
}
