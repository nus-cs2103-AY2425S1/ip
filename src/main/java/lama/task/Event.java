package lama.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represent an event task with a string description and a boolean status indicating whether the task is done.
 * Subclass of Task, that is represented by [E].
 */
public class Event extends Task {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    private static final DateTimeFormatter FILE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /** Date and time for the event start */
    protected LocalDateTime from;

    /** Date and time for the event end */
    protected LocalDateTime to;


    /**
     * Construct an event task with specified description, starting time and ending time.
     *
     * @param description Description of the event.
     * @param from Start time of the event.
     * @param to End time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Return a string representation of an event task.
     * The format of the string is "[E][status] description (from: start-time to: end-time)"
     * The start time and end time will follow the format of MMM dd yyyy HH:mm.
     * M represents the month.
     * d represents the date.
     * y represents the year.
     * H represents the hours.
     * m represents the minute.
     * Status will show "X" if done, else " ".
     *
     * @return String representation of event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DATE_FORMAT)
                + " to: " + to.format(DATE_FORMAT) + ")";
    }

    /**
     * Convert the event task to a string format that is suitable to save in file.
     * The format of string is "T | status | description | from | to".
     * The from and to follow the format of yyyy-MM-dd HHmm.
     * M represents the month.
     * d represents the date.
     * y represents the year.
     * H represents the hours.
     * m represents the minute.
     * If the status is done, it will show "1", else "0".
     *
     * @return String representation of event task in file.
     */
    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | "
                + from.format(FILE_FORMAT) + " | " + to.format(FILE_FORMAT);
    }
}
