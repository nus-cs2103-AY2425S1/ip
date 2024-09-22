package streams.task;

import java.time.LocalDateTime;

/**
 * Represents a deadline task in the task list.
 */
public class DeadlineTask extends Task {
    private LocalDateTime by;

    /**
     * Constructs a DeadlineTask with the given description and deadline.
     *
     * @param description The description of the deadline task.
     * @param by The deadline of the task.
     */
    public DeadlineTask(String description, LocalDateTime by) {
        super(description);
        assert by != null : "End time should not be null";
        this.by = by;
    }

    /**
     * Gets the deadline of the task.
     *
     * @return The deadline of the task.
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(Task.OUTPUT_FORMATTER) + ")";
    }
}
