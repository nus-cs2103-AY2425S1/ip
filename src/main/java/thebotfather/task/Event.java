package thebotfather.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringTokenizer;

import thebotfather.util.TheBotFatherException;

/**
 * Represents a task that occurs during a specific time interval.
 * An Event is a type of Task that has a start time and an end time.
 */
public class Event extends Task {

    /**
     * The start date and time of the event.
     */
    private final LocalDateTime from;

    /**
     * The end date and time of the event.
     */
    private final LocalDateTime to;

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description The description of the task.
     * @param from        The start date and time of the event.
     * @param to          The end date and time of the event.
     * @throws TheBotFatherException If the task description, start time, or end time is invalid.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) throws TheBotFatherException {
        super(description, "E");
        assert description != null && !description.trim().isEmpty() : "Description cannot be null or empty";
        assert from != null : "Start time ('from') cannot be null";
        assert to != null : "End time ('to') cannot be null";
        this.from = from;
        this.to = to;
    }

    /**
     * Creates a new Event object from a StringTokenizer input.
     * The input should contain the task description followed by "/from" and the start time,
     * then "/to" and the end time.
     *
     * @param tokens The StringTokenizer containing the input string.
     * @return A new Event object.
     * @throws TheBotFatherException If the input format is incorrect or if the start/end times are invalid.
     */
    public static Event makeEvent(StringTokenizer tokens) throws TheBotFatherException {
        try {
            StringBuilder description = new StringBuilder();
            StringBuilder from = new StringBuilder();
            StringBuilder to = new StringBuilder();
            String token = tokens.nextToken();

            while (!token.equals("/from")) {
                description.append(token).append(" ");
                token = tokens.nextToken();
            }

            token = tokens.nextToken();
            while (!token.equals("/to")) {
                from.append(token).append(" ");
                token = tokens.nextToken();
            }

            token = tokens.nextToken();
            to.append(token).append(" ");
            while (tokens.hasMoreTokens()) {
                token = tokens.nextToken();
                to.append(token).append(" ");
            }

            if (Objects.equals(from.toString(), "")) {
                throw new TheBotFatherException("Kid, look at what you have written... is that a valid event?? *sigh*\n"
                        + "If you have an event, "
                        + "type \"event <description> /from DD-MM-YY HH:MM /to DD-MM-YY HH:MM\"");
            }

            return new Event(description.toString().trim(),
                    LocalDateTime.parse(from.toString().trim(), Task.DATE_STRING_FORMATTER),
                    LocalDateTime.parse(to.toString().trim(), Task.DATE_STRING_FORMATTER));
        } catch (NoSuchElementException | DateTimeException e) {
            throw new TheBotFatherException("Kid, look at what you have written... is that a valid event?? *sigh*\n"
                    + "If you have an event, "
                    + "type \"event <description> /from DD-MM-YY HH:MM /to DD-MM-YY HH:MM\"");
        }
    }

    @Override
    public String toString() {
        return super.toString()
                + " (from: " + this.from.format(Task.DATE_STRING_FORMATTER_PRINT)
                + " to: " + this.to.format(Task.DATE_STRING_FORMATTER_PRINT)
                + ")";
    }

    @Override
    public String toFile() {
        return super.toFile() + " | " + this.from + " | " + this.to;
    }
}
