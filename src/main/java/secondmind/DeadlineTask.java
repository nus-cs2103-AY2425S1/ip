package secondmind;

/**
 * Represents a task with a deadline.
 */
public class DeadlineTask extends Task {
    private String deadline;

    /**
     * Constructor for the DeadlineTask class.
     *
     * @param task The description of the deadline task.
     * @param deadline The deadline for the task.
     */
    public DeadlineTask(String task, String deadline) {
        super(task);
        assert deadline != null: "Deadline must not be null";
        this.deadline = deadline;
    }

    /**
     * Gets the string representation of the Deadline task for storage.
     *
     * @return The storage representation of the Deadline task.
     */
    @Override
    public String getStorageRepresentation() {
        if (this.isdone) {
            return "D|1|" + this.description + "|" + this.deadline;
        } else {
            return "D|0|" + this.description + "|" + this.deadline;
        }
    }

    /**
     * Returns the string representation of the Deadline task.
     *
     * @return A string representation of the Deadline task.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[D]");
        sb.append(super.toString());
        sb.append(" (by: " + this.deadline + ")");
        return sb.toString();
    }
}