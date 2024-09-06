package BottleOpener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

/**
 * Represents an event with a start and end time.
 * <p>
 * An BottleOpener.Event task includes a description, a status indicating whether it is completed,
 * and a start and end time represented as {@link LocalDateTime}.
 * </p>
 */
public class Event extends Task {
    private static final DateTimeFormatter OUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructs a new BottleOpener.Event task with the specified description, start, and end times.
     * The task is initially marked as not done.
     * <p>
     * The start and end times are parsed from the given strings using the defined {@link #FORMATTER}.
     * </p>
     *
     * @param description The description of the BottleOpener.Event task.
     * @param start       The start time of the event as a string.
     * @param end         The end time of the event as a string.
     */
    public Event(String description, String start, String end) throws IllegalArgumentException {
        super(description);
        try {
            this.start = LocalDateTime.parse(start, Util.FORMATTER);
            this.end = LocalDateTime.parse(end, Util.FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Constructs a new BottleOpener.Event task with the specified description, status, start, and end times.
     * <p>
     * The start and end times are parsed from the given strings using the defined {@link #FORMATTER}.
     * If the times are not in a valid format, the current date and time will be used.
     * </p>
     *
     * @param description The description of the BottleOpener.Event task.
     * @param status      The completion status of the task; {@code true} if the task is done, {@code false} otherwise.
     * @param start       The start time of the event as a string.
     * @param end         The end time of the event as a string.
     */
    public Event(String description, boolean status, String start, String end) {
        super(description, status);
        try {
            this.start = LocalDateTime.parse(start, Util.FORMATTER);
            this.end = LocalDateTime.parse(end, Util.FORMATTER);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid event format!");
            this.start = LocalDateTime.now();
            this.end = LocalDateTime.now();
        }
    }

    /**
     * Constructs a new BottleOpener.Event task with the specified description, status, start, and end times.
     *
     * @param description The description of the BottleOpener.Event task.
     * @param status      The completion status of the task; {@code true} if the task is done, {@code false} otherwise.
     * @param start       The start time of the event as a {@link LocalDateTime} object.
     * @param end         The end time of the event as a {@link LocalDateTime} object.
     */
    public Event(String description, boolean status, LocalDateTime start, LocalDateTime end) {
        super(description, status);
        this.start = start;
        this.end = end;
    }

    public Event markAsDone() {
        return new Event(this.description, true, this.start, this.end);
    }

    public Event markAsUndone() {
        return new Event(this.description, false, this.start, this.end);
    }

    /**
     * Returns the type of the task.
     *
     * @return "E", representing that this task is an BottleOpener.Event.
     */
    public String getType() {
        return "E";
    }

    /**
     * Returns the start and end times of the event formatted as a string.
     *
     * @return A string in the format "start|end", where both times are formatted according to {@link #OUT_FORMAT}.
     */
    public String getTime() {
        return this.start.format(OUT_FORMAT) + "|" + this.end.format(OUT_FORMAT);
    }

    /**
     * Returns the start of the event formatted as a string.
     *
     * @return The start as a formatted string.
     */
    private String getStart() {
        return this.start.format(OUT_FORMAT);
    }

    /**
     * Returns the end of the event formatted as a string.
     *
     * @return The end as a formatted string.
     */
    private String getEnd() {
        return this.end.format(OUT_FORMAT);
    }

    /**
     * Returns a string representation of the BottleOpener.Event task, including its type, status icon, description, start, and end times.
     *
     * @return A string in the format "[type] [status icon] description (from: start to: end)".
     */
    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)", super.toString(), this.getStart(), this.getEnd());
    }

}
