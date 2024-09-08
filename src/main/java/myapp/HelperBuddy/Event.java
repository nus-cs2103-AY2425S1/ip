package myapp.helperbuddy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Event class representing an Event task
 */
public class Event extends Task {
    private final LocalDateTime eventFrom;
    private final LocalDateTime eventTo;

    /**
     * Constructs an Event task with the specified description,
     * start date/time, and end date/time.
     * @param description description of the event.
     * @param eventFrom the start date and time of the event.
     * @param eventTo the end date and time of the event.
     */
    public Event(String description, LocalDateTime eventFrom, LocalDateTime eventTo) {
        super(description);
        this.eventFrom = eventFrom;
        this.eventTo = eventTo;
    }

    /**
     * Parses a string to create an Event task.
     * The string should be in the format used for saving to a file,
     * with fields separated by " | ".
     * The fields are: task type ("E"),
     * completion status (1 for done, 0 for not done),
     * description, start date/time, and optionally, end date/time.
     * If the start or end date/time is missing or incorrectly formatted,
     * a warning is printed, and the respective date/time will be null.
     * @param taskData The string representing the Event task.
     * @return An Event task object created from the string data.
     */
    public static Event parseTask(String taskData) {
        String[] parts = taskData.split(" \\| ");
        String description = parts[2];

        LocalDateTime from = parseDateTime(parts, 3);
        LocalDateTime to = parseDateTime(parts, 4);

        Event event = new Event(description, from, to);

        if (parts.length > 1 && parts[1].trim().equals("1")) {
            event.markDone();
        }

        return event;
    }

    /**
     * Parses a date-time string from an array of string parts.
     * The method attempts to parse a date-time string at the specified index in the array.
     * If the index is out of bounds or the date-time format is invalid, it returns null.
     * @param parts The array of string parts, which contains the date-time strings.
     * @param index The index in the array where the date-time string is located.
     * @return A LocalDateTime object parsed from the string at the specified index.
     */
    public static LocalDateTime parseDateTime(String[] parts, int index) {
        if (index >= parts.length) {
            return null;
        }

        try {
            return LocalDateTime.parse(parts[index], DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            System.out.println("Warning: Invalid date format at index " + index);
            return null;
        }
    }

    /**
     * Returns a string representation of the Event task.
     * The string representation includes the task type ("E"),
     * completion status, description, and the event's start and end date/times
     * formatted as "MMM dd yyyy HH:mm".
     * For example, a completed event might be represented as:
     * "[E][X] Team meeting (from: Oct 10 2024 09:00 to: Oct 10 2024 11:00)".
     * @return A string representing the Event task.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[E][").append(this.getDone() ? "X" : " ").append("] ").append(this.getDescription());

        if (eventFrom != null) {
            sb.append(" (from: ").append(eventFrom.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")));
        }
        if (eventTo != null) {
            sb.append(" to: ").append(eventTo.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))).append(")");
        }

        return sb.toString();
    }

    /**
     * Converts the Event task into a format suitable for saving to a file.
     * The format includes the task type ("E"),
     * completion status (1 for done, 0 for not done),
     * description, and start and end date/times
     * formatted as "dd/MM/yyyy HHmm".
     * For example, a saved event might be represented as:
     * "E | 1 | Team meeting | 10/10/2024 0900 | 10/10/2024 1100".
     * @return A string representing the Event task in file format.
     */
    @Override
    public String toFileFormat() {
        StringBuilder sb = new StringBuilder();
        sb.append("E | ").append(this.getDone() ? "1" : "0").append(" | ").append(this.getDescription());

        if (eventFrom != null) {
            sb.append(" | ").append(eventFrom.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")));
        }
        if (eventTo != null) {
            sb.append(" | ").append(eventTo.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")));
        }

        return sb.toString();
    }
}
