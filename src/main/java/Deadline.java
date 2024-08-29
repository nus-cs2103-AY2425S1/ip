import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 * The {@code Deadline} class represents a task that has a specific deadline.
 * It is a subclass of {@link Task}.
 */
public class Deadline extends Task {

    /** The deadline by which the task needs to be completed. */
    protected LocalDateTime deadline;

    /** Formatter for parsing and displaying date and time. */
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");

    /**
     * Constructs a new {@code Deadline} task with the specified description and deadline.
     * The task type is set to {@link TaskType#DEADLINE}.
     *
     * @param description The description of the deadline task.
     * @param deadline    The deadline by which the task must be completed.
     */
    public Deadline(String description, String deadline) {
        super(description, TaskType.DEADLINE);
        setDeadline(deadline);

    }

    /**
     * Sets the deadline by parsing the input string into a {@link LocalDateTime} object.
     *
     * @param deadlineStr The deadline string in "yyyy-MM-dd HHmm" format.
     */
    private void setDeadline(String deadlineStr) throws DateTimeParseException {
        this.deadline = LocalDateTime.parse(deadlineStr, INPUT_FORMAT);
    }

    /**
     * Returns the deadline formatted as a string.
     *
     * @return A string representation of the deadline.
     */
    public String getFormattedDeadline() {
        return deadline != null ? deadline.format(OUTPUT_FORMAT) : "No deadline set";
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
