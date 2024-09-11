package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter INPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    private final LocalDateTime deadline;

    /**
     * Constructor for task.Deadline class.
     *
     * @param description Description of the task.
     * @param deadline    task.Deadline of the task.
     * @throws DateTimeParseException If the date and time format is invalid.
     */
    public Deadline(String description, String deadline) throws DateTimeParseException {
        super(TaskType.DEADLINE, description);
        this.deadline = LocalDateTime.parse(deadline, INPUT_DATE_FORMATTER);
    }

    /**
     * Returns the deadline of the task.
     *
     * @return task.Deadline of the task.
     */
    public String getDeadline() {
        return deadline.format(OUTPUT_DATE_FORMATTER);
    }

    /**
     * Serializes the deadline to a string.
     *
     * @return Serialized deadline.
     */
    @Override
    public String serialize() {
        return super.serialize() + " | " + deadline.format(INPUT_DATE_FORMATTER);
    }

    /**
     * Returns the string representation of the deadline.
     *
     * @return String representation of the deadline.
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + getDeadline() + ")";
    }
}
