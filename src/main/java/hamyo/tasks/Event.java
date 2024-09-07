package hamyo.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import hamyo.misc.HamyoException;

/**
 * Represents a Task that occurs between two specified Date/ Time throughout
 * the Event.
 *
 * @author Han Yu
 */
public class Event extends Task {

    private LocalDate fromDate;
    private LocalDateTime fromDateTime;
    private LocalDate toDate;
    private LocalDateTime toDateTime;

    /**
     * Constructor for Event instance.
     *
     * @param input Derived from user command input. Description and Event Start and End for the task,
     *              [Description, Event Start, Event End].
     * @throws HamyoException If user inputs an invalid Date/ Time.
     */
    public Event(String... input) throws HamyoException {
        super(input);
        setDateTime(input);
    }

    /**
     * Sets the fromDate, fromDateTime, toDate and toDateTime given user input.
     *
     * @param input Derived from user command input. Description and Event Start and End for the task,
     *              [Description, Event Start, Event End].
     * @throws HamyoException If user inputs an invalid Date/ Time.
     */
    private void setDateTime(String... input) throws HamyoException {
        try {
            LocalDateTime fromDateTimeTemp;
            LocalDateTime toDateTimeTemp;
            try {
                fromDateTimeTemp = LocalDateTime.parse(input[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            } catch (Exception e) {
                fromDateTimeTemp = null;
            } finally {
                this.fromDate = LocalDate.parse(input[1].split(" ")[0]);
            }
            try {
                toDateTimeTemp = LocalDateTime.parse(input[2], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            } catch (Exception e) {
                toDateTimeTemp = null;
            } finally {
                this.toDate = LocalDate.parse(input[2].split(" ")[0]);
            }
            this.fromDateTime = fromDateTimeTemp;
            this.toDateTime = toDateTimeTemp;
        } catch (Exception e) {
            throw new HamyoException("Invalid date/time format. yyyy-MM-dd OR yyyy-MM-dd HH:mm.");
        }
    }

    /**
     * Converts the Event representation to a standardised format for the
     * printing of TaskList.
     *
     * @return Formatted String to represent the Event.
     */
    @Override
    public String toString() {
        String fromString = this.fromDateTime != null
            ? this.fromDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + "HRS"
            : this.fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String toString = this.toDateTime != null
            ? this.toDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + "HRS"
            : this.toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E] " + super.toString() + " (from: " + fromString + " to: " + toString + ")";
    }

    /**
     * Converts the Event representation to a standardised format for the loading
     * and storing of tasks in files.
     *
     * @return Formatted String to represent the Event.
     */
    @Override
    public String toFileFormat() {
        String fromString = this.fromDateTime != null
            ? this.fromDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
            : this.fromDate.toString();
        String toString = this.toDateTime != null
            ? this.toDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
            : this.toDate.toString();
        return "E" + " | " + super.toFileFormat() + " | " + fromString + " | " + toString;
    }

    /**
     * Verify if the Event occurs on the specified date.
     *
     * @param  date The specified date to check.
     * @return true if Event falls between the specified date, false otherwise.
     */
    @Override
    public boolean occursToday(LocalDate date) {
        return (date.isAfter(this.fromDate) && date.isBefore(this.toDate))
                || date.isEqual(this.fromDate)
                || date.isEqual(this.toDate);
    }
}
