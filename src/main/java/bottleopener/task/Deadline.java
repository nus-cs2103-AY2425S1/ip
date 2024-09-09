package bottleopener.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import bottleopener.util.Util;

/**
 * Represents a task with a deadline.
 * <p>
 * A BottleOpener.Deadline task includes a description, a status indicating whether it is completed,
 * and a deadline represented as a {@link LocalDateTime}.
 * </p>
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Constructs a new Deadline task with the specified description and deadline.
     * The task is initially marked as not done.
     * <p>
     * The deadline is parsed from the given string using the defined {@link FORMATTER}.
     * </p>
     *
     * @param description The description of the Deadline task.
     * @param deadline    The deadline of the task as a string.
     */
    public Deadline(String description, String deadline) {
        super(description);
        try {
            this.deadline = LocalDateTime.parse(deadline, Util.FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Constructs a new Deadline task with the specified description, status, and deadline.
     * <p>
     * The deadline is parsed from the given string using the defined {@link FORMATTER}.
     * If the deadline is not in a valid format, the current date and time will be used.
     * </p>
     *
     * @param description The description of the Deadline task.
     * @param status      The completion status of the task; {@code true} if the task is done, {@code false} otherwise.
     * @param deadline    The deadline of the task as a string.
     */
    public Deadline(String description, boolean status, String deadline) {
        super(description, status);
        try {
            this.deadline = LocalDateTime.parse(deadline, Util.FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Constructs a new Deadline task with the specified description, status, and deadline.
     *
     * @param description The description of the Deadline task.
     * @param status      The completion status of the task; {@code true} if the task is done, {@code false} otherwise.
     * @param deadline    The deadline of the task as a {@link LocalDateTime} object.
     */
    public Deadline(String description, boolean status, LocalDateTime deadline) {
        super(description, status);
        this.deadline = deadline;
    }

    public Deadline markAsDone() {
        return new Deadline(this.description, true, this.deadline);
    }

    public Deadline markAsUndone() {
        return new Deadline(this.description, false, this.deadline);
    }

    /**
     * Returns the type of the task.
     *
     * @return "D", representing that this task is a Deadline.
     */
    public String getType() {
        return "D";
    }

    /**
     * Returns the deadline of the task formatted as a string.
     *
     * @return The deadline as a formatted string.
     */
    public String getTime() {
        return this.deadline.format(Util.OUT_FORMAT);
    }

    /**
     * Returns a string representation of the Deadline task, including its type, status icon, description, and deadline.
     *
     * @return A string in the format "[type] [status icon] description (by: deadline)".
     */
    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), this.getTime());
    }

}
