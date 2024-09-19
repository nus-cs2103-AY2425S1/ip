package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 * The deadline is represented by a date.
 */
public class Deadline extends Task {

    /** The date by which the task should be completed. */
    protected LocalDate by;

    /**
     * Constructs a Deadline task with the specified description and due date.
     *
     * @param description The description of the task.
     * @param by The due date of the task in the format "yyyy-MM-dd".
     * @throws DeadlineException If the by date cannot be parsed.
     */
    public Deadline(String description, String by) throws DeadlineException {
        super(description);

        // Parse datetime
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DeadlineException("Cannot parse due date");
        }
    }

    /**
     * Returns a formatted string representation of the task for storage.
     *
     * @return A string representing the task in a format suitable for storage.
     */
    @Override
    public String toDataFormat() {
        return "deadline | " + super.toDataFormat() + " | " + by;
    }

    /**
     * Returns a formatted string representation of the task for display,
     * including its status icon and description.
     *
     * @return A string representing the task with its deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
