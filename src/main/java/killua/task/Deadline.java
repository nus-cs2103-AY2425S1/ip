package killua.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline in the Killua application.
 * This class handles deadlines that can be scheduled with a specific date-time or date.
 */
public class Deadline extends Task {

    protected LocalDateTime dateTime;
    protected LocalDate date;

    /**
     * Constructs a Deadline with a specified date-time.
     *
     * @param description Description of the deadline.
     * @param dateTime The date-time of the deadline.
     */
    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Constructs a Deadline with a specified date.
     *
     * @param description Description of the deadline.
     * @param date The date of the deadline.
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    /**
     * Formats the deadline date or date-time into a string.
     * Uses "MMM d yyyy HH:mm" format for date-time and "MMM d yyyy" format for dates.
     *
     * @return A formatted string representing the deadline.
     */
    public String format() {
        if (dateTime != null) {
            return dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
        } else if (date != null) {
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
        return "";
    }

    /**
     * Returns the date of the deadline.
     * If the deadline uses LocalDateTime, it returns the date part of the date-time.
     *
     * @return The date of the deadline.
     */
    public LocalDate getDate() {
        if (date != null) {
            return date;
        }
        return dateTime.toLocalDate();
    }

    /**
     * Returns the date-time of the deadline.
     *
     * @return The date-time of the deadline, or null if the deadline uses LocalDate.
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Returns a string representation of this deadline in a format suitable for saving to a file.
     * The format includes the deadline type, completion status, description, and the date or date-time.
     *
     * @return A string representation of the deadline for saving.
     */
    @Override
    public String toSave() {
        return "D" + super.toSave() + " | " + format();
    }

    /**
     * Returns a string representation of this deadline in a user-friendly format.
     * The format includes the deadline type, completion status, description, and the date or date-time.
     *
     * @return A string representation of the deadline for display.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + format() + ")";
    }
}
