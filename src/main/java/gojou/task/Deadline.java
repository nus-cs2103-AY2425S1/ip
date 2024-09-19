package gojou.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task with a deadline.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Constructs a Deadline Task with the specified details.
     *
     * @param taskName The name or description of the task.
     * @param isCompleted A boolean indicating whether the task is completed.
     * @param deadline The deadline for the task as a LocalDateTime object.
     */
    public Deadline(String taskName, boolean isCompleted, Priority priority, LocalDateTime deadline) {
        super(taskName, isCompleted, priority);
        this.deadline = deadline;
    }

    /**
     * {@inheritDoc}
     *
     * Returns the String representation of the Task to be displayed to the user
     * that includes its completion status, description, and deadline.
     *
     * @return A String representation of the Deadline Task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy h.mma")) + ")";
    }

    /**
     * Returns a String representation of the Deadline Task suitable for storage.
     * The format includes the task details and the deadline in "yyyy-MM-dd HHmm" format.
     *
     * @return A String representation of the Deadline Task for storage.
     */
    @Override
    public String toStorageString() {
        return "[D]" + super.toStorageString() + " /by "
                + this.deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
}

