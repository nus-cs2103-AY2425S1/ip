package task;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * A Task with a specific start and end time.
 *
 * @author Toh Yi Hui A0259080A
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructor for a new Event task.
     *
     * @param description the description of the task.
     * @param start the start time of the task.
     * @param end the end time of the task.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * constructor for a new Event with the specified status of the Event.
     *
     * @param description the description of the task.
     * @param iscomplete the status of the task.
     * @param start the start time of the task.
     * @param end the end time of the task.
     */
    public Event(String description, boolean isComplete, LocalDateTime start,
            LocalDateTime end) {
        super(description, isComplete);
        this.start = start;
        this.end = end;
    }

    /**
     * Return the start time of the task.
     *
     * @return the start time of the task.
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * Return the end time of the task.
     *
     * @return the end time of the task.
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Return the String representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                parseDateTimeToString(start), parseDateTimeToString(end));
    }

    /**
     * Return the String representation of the date time.
     * Format example: 7 July Monday, 16:00H
     *
     * @param dateTime the date time to parse.
     * @return the formatted String.
     */
    private String parseDateTimeToString(LocalDateTime dateTime) {
        String dayOfMonth = String.valueOf(dateTime.getDayOfMonth());
        String month = dateTime.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
        String dayOfWeek = dateTime.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        String hour = dateTime.getHour() < 10
                ? "0" + String.valueOf(dateTime.getHour())
                : String.valueOf(dateTime.getHour());
        String minute = dateTime.getMinute() < 10
                ? "0" + String.valueOf(dateTime.getMinute())
                : String.valueOf(dateTime.getMinute());
        return String.format("%s %s %s, %s:%sH", dayOfMonth, month, dayOfWeek, hour, minute);
    }
}
