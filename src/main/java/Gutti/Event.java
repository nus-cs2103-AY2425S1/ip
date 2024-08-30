package Gutti;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

/**
 * The {@code Event} class represents a task that spans a specific period of time, starting and ending at particular dates/times.
 * <p>
 * It extends the {@code Task} class and is marked with an "E" to indicate that it is an Event task.
 * </p>
 */
public class Event extends Task {
    /** The start date/time of the event. */
    protected LocalDateTime from;
    /** The end date/time of the event. */
    protected LocalDateTime  to;

    private static final List<DateTimeFormatter> FORMATTERS = Arrays.asList(
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
            DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"),
            DateTimeFormatter.ofPattern("MMM dd yyyy h:mma"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")
    );


    /**
     * Constructs a new {@code Event} task with the specified description, start date/time, and end date/time.
     *
     * @param description The description of the Event task.
     * @param from The start date/time of the event.
     * @param to The end date/time of the event.
     */
    public Event(String description, String from, String to,boolean isDone) {
        super(description,isDone);
        this.from = parseDateTime(from);
        this.to = parseDateTime(to);
    }

    /**
     * Parses the date/time string into a {@code LocalDateTime} object using multiple predefined formats.
     * If none of the formats match, returns {@code null}.
     *
     * @param dateTime The date/time string to parse.
     * @return The parsed {@code LocalDateTime} object or {@code null} if parsing fails.
     */
    private LocalDateTime parseDateTime(String dateTime) {
        for (DateTimeFormatter formatter : FORMATTERS) {
            try {
                return LocalDateTime.parse(dateTime, formatter);
            } catch (DateTimeParseException e) {
                // Continue trying other formats
            }
        }
        System.out.println("Invalid date format. Please use yyyy-MM-dd HHmm,d/MM/yyyy HHmm, dd/MM/yyyy HHmm format or MMM dd yyyy h:mma.");
        return null; // Return null if no format matched
    }

    /**
     * Returns a string representation of the {@code Event} task.
     * <p>
     * The string includes the task type identifier "[E]" followed by the status icon,
     * description, and the date/time range of the event.
     * </p>
     *
     * @return A string representation of the Event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");
        return "[E][" + (isDone ? "X" : " ") + "] " + description + " (from: " +
                (from != null ? from.format(formatter) : "Invalid date")
                        + " to: " + (to != null ? to.format(formatter) : "Invalid date") + ")";
    }
}