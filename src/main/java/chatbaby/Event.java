package chatbaby;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event in the ChatBaby application.
 * Extends the Task class to include details about the event's duration.
 */
public class Event extends Task {
    private static final DateTimeFormatter INPUT_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter OUTPUT_FORMATTER =
            DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
    private LocalDateTime eventStartTime;
    private LocalDateTime eventEndTime;

    /**
     * Constructs an Event with the specified name, start, and end times.
     *
     * @param name The name of the event.
     * @param eventStartTime The start time of the event in "yyyy-MM-dd HH:mm" format.
     * @param eventEndTime The end time of the event in "yyyy-MM-dd HH:mm" format.
     */
    public Event(String name, String eventStartTime, String eventEndTime) {
        super(name);
        this.eventStartTime = LocalDateTime.parse(eventStartTime, INPUT_FORMATTER);
        this.eventEndTime = LocalDateTime.parse(eventEndTime, INPUT_FORMATTER);
    }

    /**
     * Constructs an Event with the specified name, start, and end times.
     *
     * @param name The name of the event.
     * @param eventStartTime The start time of the event as a LocalDateTime object.
     * @param eventEndTime The end time of the event as a LocalDateTime object.
     */
    public Event(String name, LocalDateTime eventStartTime, LocalDateTime eventEndTime) {
        super(name);
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
    }

    /**
     * Gets the end time of the event.
     *
     * @return The end time of the event as a LocalDateTime object.
     */
    public LocalDateTime getEventEndTime() {
        return eventEndTime;
    }

    /**
     * Converts the event to a string format suitable for saving to a file.
     *
     * @return A string representing the event in file format.
     */
    @Override
    public String toFileText() {
        return "E | " + (this.isDone() ? "1" : "0") + " | " + this.getName() + " | "
                + eventStartTime.format(OUTPUT_FORMATTER) + " | " + eventEndTime.format(OUTPUT_FORMATTER);
    }

    /**
     * Converts the event to a string format suitable for displaying to the user.
     *
     * @return A string representing the event in user-friendly format.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + eventStartTime.format(OUTPUT_FORMATTER)
                + " to: " + eventEndTime.format(OUTPUT_FORMATTER) + ")";
    }
}
