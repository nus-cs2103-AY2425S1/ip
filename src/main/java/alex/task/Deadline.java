package alex.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task with a deadline.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;
    public Deadline (String taskName, boolean taskCompletionStatus, LocalDateTime deadline) {
        super(taskName, taskCompletionStatus);
        this.deadline = deadline;
    }

    /**
     * {@inheritDoc}
     *
     * Returns the String representation of the Task to be displayed to user
     * that includes its completion status, description and deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy h.mma")) + ")";
    }

    @Override
    public String storageString() {
        return "[D]" + super.toString() + " /by " + this.deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
}
