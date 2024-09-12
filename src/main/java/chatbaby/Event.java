package chatbaby;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event in the ChatBaby application.
 * Extends the Task class to include details about the event's duration.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs an Event with the specified name, start, and end times.
     *
     * @param name The name of the event.
     * @param from The start time of the event in "yyyy-MM-dd HH:mm" format.
     * @param to The end time of the event in "yyyy-MM-dd HH:mm" format.
     */
    public Event(String name, String from, String to) {
        super(name);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.from = LocalDateTime.parse(from, formatter);
        this.to = LocalDateTime.parse(to, formatter);
    }

    /**
     * Constructs an Event with the specified name, start, and end times.
     *
     * @param name The name of the event.
     * @param from The start time of the event as a LocalDateTime object.
     * @param to The end time of the event as a LocalDateTime object.
     */
    public Event(String name, LocalDateTime from, LocalDateTime to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    /**
     * Gets the end time of the event.
     *
     * @return The end time of the event as a LocalDateTime object.
     */
    public LocalDateTime getTo() {
        return to;
    }

    /**
     * Converts the event to a string format suitable for saving to a file.
     *
     * @return A string representing the event in file format.
     */
    @Override
    public String toFileText() {
        assert from.toString().matches("\\d{4}-\\d{2}-\\d{2}")
                : "Date must be in the format yyyy-MM-dd";
        assert to.toString().matches("\\d{4}-\\d{2}-\\d{2}")
                : "Date must be in the format yyyy-MM-dd";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return "E | " + (this.isDone() ? "1" : "0") + " | " + this.getName() + " | "
                + from.format(formatter) + " | " + to.format(formatter);
    }

    /**
     * Converts the event to a string format suitable for displaying to the user.
     *
     * @return A string representing the event in user-friendly format.
     */
    @Override
    public String toString() {
        assert from.toString().matches("\\d{4}-\\d{2}-\\d{2}")
                : "Date must be in the format yyyy-MM-dd";
        assert to.toString().matches("\\d{4}-\\d{2}-\\d{2}")
                : "Date must be in the format yyyy-MM-dd";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return "[E]" + super.toString() + " (from: " + from.format(formatter)
                + " to: " + to.format(formatter) + ")";
    }
}
