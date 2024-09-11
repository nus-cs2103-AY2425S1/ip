package carter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline in Carter.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a new Deadline task with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param by The date and time by which the task should be completed.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);

        assert by != null : "time should not be null";
        this.by = by;
    }

    /**
     * Constructs a new Deadline task with the specified description,
     * deadline, and completion status.
     *
     * @param description The description of the task.
     * @param by The date and time by which the task should be completed.
     * @param isDone The completion status of the task.
     */
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        assert by != null : "time should not be null";
        this.by = by;
    }

    /**
     * Returns a string representation of the task, including its type, description,
     * and deadline.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    /**
     * Returns a string representation of the task formatted for saving to a file.
     *
     * @return A string representation of the task formatted for file storage.
     */
    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " |"
                + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
