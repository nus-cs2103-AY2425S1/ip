package moimoi.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import moimoi.exception.InvalidDateTimeRangeException;

/**
 * Represents an event task.
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an event task of the specified description, starting date-time and ending date-time.
     *
     * @param description Task description.
     * @param from Task's starting date-time.
     * @param to Task's ending date-time.
     * @throws InvalidDateTimeRangeException If the specified starting date-time is later
     *                                       than the ending date-time.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to)
            throws InvalidDateTimeRangeException {
        super(TaskEnum.E, description);
        if (from.isAfter(to)) {
            throw new InvalidDateTimeRangeException();
        } else {
            this.from = from;
            this.to = to;
        }
    }

    /**
     * Checks if the task occurs on the specified date.
     *
     * @param date Date to be checked against the task's date range.
     * @return true if the task occurs on the specified date; false otherwise.
     */
    @Override
    public boolean occurringOn(LocalDate date) {
        if (!this.from.toLocalDate().isAfter(date) && !this.to.toLocalDate().isBefore(date)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the String representation of the task for UI display.
     *
     * @return String representation of the task for UI display.
     */
    @Override
    public String stringUI() {
        return super.stringUI() + " (from: "
                + this.from.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) + " to: "
                + this.to.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) + ")";
    }

    /**
     * Returns the String representation of the task for storage.
     *
     * @return String representation of the task for storage.
     */
    @Override
    public String stringStorage() {
        return super.stringStorage() + " | "
                + this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))+ " | "
                + this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

}
