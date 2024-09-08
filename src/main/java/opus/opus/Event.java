package opus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task with a start date and an end date.
 * The event dates are parsed from a string using {@code yyyy-MM-dd} format.
 */
public class Event extends Task {
    private LocalDate fromDateTime;
    private LocalDate toDateTime;
    private String fromString;
    private String toString;

    /**
     * Constructs an Event task with the specified description, start date, and end date.
     * If the dates cannot be parsed, they are stored as strings.
     *
     * @param description The description of the event.
     * @param from The start date of the event in {@code yyyy-MM-dd} format.
     * @param to The end date of the event in {@code yyyy-MM-dd} format.
     */
    public Event(String description, String from, String to) {
        super(description);
        try {
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            this.fromDateTime = LocalDate.parse(from, inputFormat);
            this.toDateTime = LocalDate.parse(to, inputFormat);
        } catch (DateTimeParseException e) {
            this.fromString = from;
            this.toString = to;
        }
    }

    /**
     * Returns the formatted start date. If the date was successfully parsed, it is
     * returned in {MMM dd yyyy} format. Otherwise, the original string is returned.
     *
     * @return The formatted start date or the original string if parsing failed.
     */
    private String getFrom() {
        if (fromDateTime != null) {
            DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
            return fromDateTime.format(outputFormat);
        } else {
            return fromString;
        }
    }

    /**
     * Returns the formatted end date. If the date was successfully parsed, it is
     * returned in {MMM dd yyyy} format. Otherwise, the original string is returned.
     *
     * @return The formatted end date or the original string if parsing failed.
     */
    private String getTo() {
        if (toDateTime != null) {
            DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
            return toDateTime.format(outputFormat);
        } else {
            return toString;
        }
    }

    /**
     * Returns the save format for the event task to be written to storage.
     *
     * @return The formatted string for saving the event task.
     */
    @Override
    public String toSaveFormat() {
        return "E|" + (isDone ? "1" : "0") + "|" + description + "|" + getFrom() + "|" + getTo();
    }

    /**
     * Creates an Event object from a formatted string read from storage.
     *
     * @param fullLine The line read from the storage file.
     * @return A new Event object based on the parsed string.
     */
    public static Task fromFileFormat(String fullLine) {
        String[] parts = fullLine.split("\\|");
        Event event = new Event(parts[2], parts[3], parts[4]);
        if (parts[1].equals("1")) {
            event.markAsDone();
        }
        return event;
    }

    /**
     * Returns the string representation of the event, including its description,
     * start date, end date, and whether it is done.
     *
     * @return The string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + (isDone ? "[X] " : "[ ] ") + description + " (from: " + getFrom() + " to: " + getTo() + ")";
    }
}
