package moimoi.util.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import moimoi.util.exception.InvalidDateTimeRangeException;

/**
 * Represents an event task.
 */
public class Event extends Task {

    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Constructs an event task of the specified description, starting date-time and ending date-time.
     *
     * @param description Task description.
     * @param start Task's starting date-time.
     * @param end Task's ending date-time.
     * @throws InvalidDateTimeRangeException If the specified starting date-time is later
     *                                       than the ending date-time.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end)
            throws InvalidDateTimeRangeException {
        super(TaskEnum.E, description);

        if (start.isAfter(end)) {
            throw new InvalidDateTimeRangeException();
        }

        this.start = start;
        this.end = end;
    }

    /**
     * Checks if the task occurs on the specified date.
     *
     * @param date Date to be checked against the task's date range.
     * @return true if the task occurs on the specified date; false otherwise.
     */
    @Override
    public boolean occursOn(LocalDate date) {
        boolean isStartOnOrBefore = !this.start.toLocalDate().isAfter(date);
        boolean isEndOnOrAfter = !this.end.toLocalDate().isBefore(date);
        return isStartOnOrBefore && isEndOnOrAfter;
    }

    /**
     * Returns the String representation of the task for UI display.
     *
     * @return String representation of the task for UI display.
     */
    @Override
    public String stringUI() {
        return super.stringUI() + " (from: "
                + this.start.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) + " to: "
                + this.end.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) + ")";
    }

    /**
     * Returns the String representation of the task for storage.
     *
     * @return String representation of the task for storage.
     */
    @Override
    public String stringStorage() {
        return super.stringStorage() + " | "
                + this.start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + " | "
                + this.end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

}
