package Hamyo.Tasks;

import Hamyo.Misc.HamyoException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task that occurs between two specified Date/ Time throughout
 * the Event.
 *
 * @author Han Yu
 */
public class Event extends Task {

    private final LocalDate fromDate;
    private final LocalDateTime fromDateTime;
    private final LocalDate toDate;
    private final LocalDateTime toDateTime;

    /**
     * Constructor for Event instance.
     *
     * @param task Description for the task, stored in String array.
     *             [Description, Event Start, Event End].
     * @throws HamyoException If user inputs an invalid Date/ Time.
     */
    public Event(String[] task) throws HamyoException {
        super(task);
        try {
            LocalDateTime fromDateTimeTemp;
            LocalDateTime toDateTimeTemp;
            try {
                fromDateTimeTemp = LocalDateTime.parse(task[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            } catch (Exception e) {
                fromDateTimeTemp = null;
            } finally {
                this.fromDate = LocalDate.parse(task[1].split(" ")[0]);
            }
            try {
                toDateTimeTemp = LocalDateTime.parse(task[2], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            } catch (Exception e) {
                toDateTimeTemp = null;
            } finally {
                this.toDate = LocalDate.parse(task[2].split(" ")[0]);
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
        String fromString = this.fromDateTime != null ?
            this.fromDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + "HRS":
            this.fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String toString = this.toDateTime != null ?
            this.toDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"))  + "HRS":
            this.toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
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
        String fromString = this.fromDateTime != null ?
            this.fromDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")):
            this.fromDate.toString();
        String toString = this.toDateTime != null ?
            this.toDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) :
            this.toDate.toString();
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
        return (date.isAfter(this.fromDate) && date.isBefore(this.toDate)) || date.isEqual(this.fromDate) || date.isEqual(this.toDate);
    }
}
