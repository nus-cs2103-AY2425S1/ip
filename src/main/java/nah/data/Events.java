package nah.data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Events class represents a task that occurs during a specific
 * time period, with a start and end time. It has 2 LocalDateTime fields
 * to store the start and end times.
 *
 * <p>This class provides methods to retrieve the end time, format the task
 * for storage, and check if the task type is "event".</p>
 */
public class Events extends Task {
    private static final String TIME_PATTERN = "MMM d yyyy, h:mm a";
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructor.
     * @param content the description of the task
     * @param start start of the event
     * @param end end of the event
     */
    public Events(String content, LocalDateTime start, LocalDateTime end) {
        super(content);
        assert start != null && end != null : "Start and End times cannot be null";
        assert start.isBefore(end) : "Start time must be before End time";
        this.start = start;
        this.end = end;
    }

    /**
     * Checks if the end of event is before due.
     *
     * @return a LocalDateTime value
     */
    @Override
    public boolean isBefore(LocalDateTime due) {
        return this.end.isBefore(due);
    }

    /**
     * Return a brief description of task to be stored in a hard disk.
     *
     * @return a String
     */
    @Override
    public String brief() {
        return "E | " + super.getStatus() + " | " + super.getTask() + " | "
                + this.start.format(DateTimeFormatter.ofPattern(TIME_PATTERN))
                + " | " + this.end.format(DateTimeFormatter.ofPattern(TIME_PATTERN));

    }

    /**
     * Checks if the String to lowercase is "event".
     *
     * @param s the String that need to be checked
     * @return a boolean value
     */
    @Override
    public boolean isReferingToTask(String s) {
        assert s != null : "Task reference cannot be null";
        return s.trim().toLowerCase().equals("event");
    }

    /**
     * Returns String representation.
     *
     * @return a String
     */
    @Override
    public String toString() {
        return "[E] "
                + super.toString()
                + " (from: "
                + this.start.format(DateTimeFormatter.ofPattern(TIME_PATTERN))
                + " to: "
                + this.end.format(DateTimeFormatter.ofPattern(TIME_PATTERN))
                + ")";
    }
}
