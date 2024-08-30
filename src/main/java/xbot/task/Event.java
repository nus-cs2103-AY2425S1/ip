package xbot.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents a task with a event in the XBot application.
 * A {@code Event} task has a description, a start time and an end time.
 */
public class Event extends Task {
    protected String from, to;

    /**
     * Constructs a new {@code Event} task with the specified description, start time and end time.
     *
     * @param description The description of the task.
     * @param from The start time of the task.
     * @param to The end time of the task.
     */
    public Event(String description, String from, String to) {
        super(description, TaskType.E);
        this.from = from;
        this.to = to;
    }

    /**
     * Converts the deadline to a different date format.
     * The method attempts to parse the deadline using multiple date formats.
     * If successful, it returns the date in a standard format; otherwise, it returns an error message.
     *
     * @param by The original deadline string to be converted.
     * @return The formatted deadline string, or an error message if the conversion fails.
     */
    public String changeDateFormat(String by) {

        List<String> formats = new ArrayList<>();
        formats.add("yyyy-MM-dd");
        formats.add("d/M/yyyy");
        formats.add("d/M/yyyy HHmm");

        for (String format : formats) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            try {
                if (format.contains("HHmm")) {
                    LocalDateTime dateTime = LocalDateTime.parse(by, formatter);
                    return dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma"));
                } else {
                    LocalDate date = LocalDate.parse(by, formatter);
                    return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                }
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        return "TimeDate cannot be converted to another format :'0";
    }

    /**
     * Returns the start time of event.
     *
     * @return The start time as a String.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Returns the end time of event.
     *
     * @return The end time as a String.
     */
    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + changeDateFormat(from) + " to: " + changeDateFormat(to) + ")";
    }
}
