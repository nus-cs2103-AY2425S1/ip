package stan.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import stan.exceptions.StanInvalidDateTimeFormatException;

/**
 * Represents a Deadline task in the task list.
 * A Deadline task has a specific date and time by which it must be completed.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructor for Deadline.
     *
     * @param description The description of the deadline.
     * @param by The date and time by which the task must be completed, in the format yyyy-MM-dd HHmm.
     * @throws StanInvalidDateTimeFormatException If the date and time format is incorrect.
     */
    public Deadline(String description, String by) throws StanInvalidDateTimeFormatException {
        super(description);
        this.by = parseDateTime(by);
    }

    /**
     * Parses a date and time string into a LocalDateTime object.
     *
     * @param dateTime The date and time string to be parsed.
     * @return The parsed LocalDateTime object.
     * @throws StanInvalidDateTimeFormatException If the date and time format is incorrect.
     */
    private LocalDateTime parseDateTime(String dateTime) throws StanInvalidDateTimeFormatException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return LocalDateTime.parse(dateTime, formatter);
        } catch (DateTimeParseException e) {
            throw new StanInvalidDateTimeFormatException("The deadline time must be in the format yyyy-MM-dd HHmm.\n"
                    + "E.g. 2021-07-29 2359");
        }
    }

    /**
     * Converts the Deadline task to a storage-friendly string format.
     *
     * @return The string representation of the Deadline task for storage.
     */
    @Override
    public String toStorageString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return String.format("D | %s | %s | %s", (isDone ? "1" : "0"), this.description, by.format(formatter));
    }

    /**
     * Returns the string representation of the Deadline task.
     *
     * @return The string representation of the Deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }
}
