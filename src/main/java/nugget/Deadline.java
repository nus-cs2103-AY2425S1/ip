package nugget;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline that needs to be completed by a specific date and time.
 * A <code>Deadline</code> task includes a description and a date/time by which it should be completed.
 */
public class Deadline extends Task {
    private LocalDateTime dateTime;

    /**
     * Constructs a <code>Deadline</code> task with the specified description and deadline date.
     *
     * @param description The description of the task.
     * @param date The deadline date in the format "yyyy-MM-dd HHmm".
     * @throws DateTimeParseException if the date is not in the expected format.
     */
    public Deadline(String description, String date) throws DateTimeParseException {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.dateTime = LocalDateTime.parse(date, formatter);
    }

    /**
     * Returns the task type indicator for a deadline task.
     *
     * @return The string "[D]" indicating this is a deadline task.
     */
    public String getTaskType() {
        return "[D]";
    }

    /**
     * Returns the string representation of the deadline task, including its type,
     * description, and formatted deadline date.
     *
     * @return A formatted string representing the deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");
        return this.getTaskType() + super.toString() + " (by: " + this.dateTime.format(formatter) + ")";
    }
}
