package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.exceptions.InvalidDeadlineException;
import duke.parser.DateTimeFormatEnum;

/**
 * Represents a deadline task in the DailyTasks application.
 * A deadline task has a description and a specific datetime by which the task must be completed.
 */
public class Deadline extends Task {

    /** The datetime by which the deadline task must be completed. */
    protected LocalDateTime parsedDateTime;

    /**
     * Creates a new Deadline task with the specified description and deadline datetime.
     * Validates that the datetime is properly formatted.
     *
     * @param description The description of the deadline task.
     * @param by The deadline datetime in string format.
     * @throws InvalidDeadlineException If the provided datetime is invalid.
     */
    public Deadline(String description, String by) throws InvalidDeadlineException {
        super(description);

        parsedDateTime = DateTimeFormatEnum.parse(by)
                .orElseThrow(() -> new IllegalArgumentException("Invalid date format."));;

        if (parsedDateTime == null) {
            throw new InvalidDeadlineException("Your deadline is invalid.");
        }
    }

    /**
     * Returns the formatted deadline datetime.
     *
     * @return The formatted deadline datetime as a string (e.g., "MMM d yyyy, h:mm a").
     */
    public String getBy() {
        return parsedDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"));
    }

    /**
     * Checks if the deadline task is occurring at a specific date and time.
     *
     * @param taskDate The date and time to check.
     * @return true if the task is occurring at the specified date and time, false otherwise.
     */
    @Override
    public boolean occurring(LocalDateTime taskDate) {
        return taskDate != null && taskDate.equals(this.parsedDateTime);
    }

    /**
     * Returns a string representation of the deadline task, including its type identifier,
     * status icon, description, and the formatted deadline datetime.
     *
     * @return The string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + " [" + this.getStatusIcon() + "] " + "[Priority: " + this.priority + "] "
                + super.toString() + " (by: " + this.getBy() + ")";
    }
}
