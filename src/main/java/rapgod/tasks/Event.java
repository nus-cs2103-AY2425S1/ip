package rapgod.tasks;

import rapgod.exceptions.InvalidDateTimeException;
import rapgod.utils.Parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event with a description and a time range.
 * This subclass of {@link Task} includes information about when the event starts and ends.
 */
public class Event extends Task{
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event with the specified description, start time, and end time.
     * The times are parsed into {@link LocalDateTime} objects using the {@link Parser} class.
     *
     * @param description The description of the event.
     * @param from The start time of the event, provided as a string.
     * @param to The end time of the event, provided as a string.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = Parser.parseToDateTime(from);
        this.to = Parser.parseToDateTime(to);

        if (this.from.isAfter(this.to)) {
            throw new InvalidDateTimeException("Start date and time cannot be later than end date and time!");
        }

    }

    /**
     * Returns the start time of the event.
     *
     * @return The start time of the event as a {@link LocalDateTime} object.
     */
    public LocalDateTime getFrom() {
        return this.from;
    }

    /**
     * Returns the end time of the event.
     *
     * @return The end time of the event as a {@link LocalDateTime} object.
     */
    public LocalDateTime getTo() {
        return this.to;
    }

    public void setFrom(String from) {
        this.from = Parser.parseToDateTime(from);
    }

    public void setTo(String to) {
        this.to = Parser.parseToDateTime(to);
    }

    /**
     * Returns a string representation of the Event.
     * The string includes the task type identifier "[E]", the completion status, the description of the event,
     * and the start and end times formatted as "MMM dd yyyy hh:mma".
     * If the time is "12:00am", it is omitted from the display.
     *
     * @return A string representation of the Event in the format "[E] [status] description (from: startTime to: endTime)".
     */
    @Override
    public String toString() {
        String mark = isDone ? "X" : " ";
        String from = this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma"));
        String to = this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma"));

        if(from.endsWith("12:00am")) {
            from = from.substring(0, from.length() - 8);
        }

        if(to.endsWith("12:00am")) {
            to = to.substring(0, to.length() - 8);
        }
        return String.format("[E] [%s] %s (from: %s to: %s)", mark, super.description, from, to);
    }

}
