package matcha.task;
import java.time.LocalDateTime;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a Deadline task given a description and deadline.
     *
     * @param description Description of the deadline.
     * @param by Deadline of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of the Deadline task when saving to a file.
     *
     * @return String representation of the Deadline task when saving to a file.
     */
    @Override
    public String toSaveString() {
        return "D | " + super.toSaveString() + " | " + this.by;
    }

    /**
     * Returns the string representation of the Deadline task when printing to the user.
     *
     * @return String representation of the Deadline task when printing to the user.
     */
    @Override
    public String toString() {
        return "\t[D]" + super.toString() + " (by: " + this.by.format(Task.getOutputFormat()) + ")";
    }
}
