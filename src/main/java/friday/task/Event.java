package friday.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task. An Event is a task that occurs over a specified time period.
 */
public class Event extends Task {
    private static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd HHmm";
    private static final String DISPLAY_FORMAT_PATTERN = "MMM dd yyyy, h:mm a";
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs an Event with the specified description, start time, end time, and completion status.
     *
     * @param description The description of the Event.
     * @param from        The start time of the Event in "yyyy-MM-dd HHmm" format.
     * @param to          The end time of the Event in "yyyy-MM-dd HHmm" format.
     * @param isDone      True if the task is done, false otherwise.
     */
    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN));
        this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN));
    }

    /**
     * Constructs an Event with the specified description, start time, and end time.
     * The task is not done by default.
     *
     * @param description The description of the Event.
     * @param from        The start time of the Event in "yyyy-MM-dd HHmm" format.
     * @param to          The end time of the Event in "yyyy-MM-dd HHmm" format.
     */
    public Event(String description, String from, String to) {
        this(description, from, to, false);
    }

    public LocalDate getFrom() {
        return from.toLocalDate();
    }

    public LocalDate getTo() {
        return to.toLocalDate();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern(DISPLAY_FORMAT_PATTERN))
                + " to: " + to.format(DateTimeFormatter.ofPattern(DISPLAY_FORMAT_PATTERN)) + ")";
    }

    @Override
    public String toFileFormat() {
        return "E | " + (super.isTaskDone() ? "1" : "0") + " | " + super.getDescription() + " | "
                + from.format(DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN)) + " | "
                + to.format(DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN));
    }
}