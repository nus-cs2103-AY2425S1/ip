package snipe.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The {@code Deadline} class represents a task that has a specific deadline.
 * It is a subclass of {@link Task}.
 */
public class Deadline extends Task {

    /** The deadline by which the task needs to be completed. */
    protected LocalDateTime deadline;

    /** Formatter for parsing date and time from the input format. */
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /** Formatter for displaying date and time in the output format. */
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");

    /**
     * Constructs a new {@code Deadline} task with the specified description and deadline.
     * The task type is set to {@link TaskType#DEADLINE}.
     *
     * @param description The description of the deadline task.
     * @param deadline    The deadline by which the task must be completed, in the format "yyyy-MM-dd HHmm".
     * @throws DateTimeParseException If the provided deadline string cannot be parsed.
     */
    public Deadline(String description, String deadline) {
        super(description, TaskType.DEADLINE);
        setDeadline(deadline);

    }

    /**
     * Sets the deadline by parsing the input string into a {@link LocalDateTime} object.
     *
     * @param deadlineStr The deadline string in "yyyy-MM-dd HHmm" format.
     * @throws DateTimeParseException If the provided deadline string cannot be parsed.
     */
    private void setDeadline(String deadlineStr) throws DateTimeParseException {
        this.deadline = LocalDateTime.parse(deadlineStr, INPUT_FORMAT);
    }

    /**
     * Returns the deadline formatted as a string for display purposes.
     *
     * @return A string representation of the deadline in the format "MMM dd yyyy, hh:mm a",
     * or "No deadline set" if the deadline is null.
     */
    public String getFormattedDeadline() {
        return deadline != null ? deadline.format(OUTPUT_FORMAT) : "No deadline set";
    }

    /**
     * Returns a string representation of the task formatted for storage in a file.
     *
     * @return A string representation of the task in the format "D | status | description | deadline".
     */
    @Override
    public String parseToFileFormat() {
        String status = getStatus() ? "1" : "0";
        String formattedDeadline = deadline != null ? deadline.format(INPUT_FORMAT) : "";
        return "D | " + status + " | " + this.description + " | " + formattedDeadline;
    }

    /**
     * Returns a string representation of the deadline task,
     * including its type indicator, description, and deadline.
     *
     * @return A string in the format: "[D][status] description (by: deadline)".
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getFormattedDeadline() + ")";
    }
}
