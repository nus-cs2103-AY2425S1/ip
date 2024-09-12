package fred.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event class represents a task that occurs within a specific time frame.
 * It extends the Task class and includes a start time and an end time.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    DateTimeFormatter formatter;

    /**
     * Constructs an Event task with the given description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from The start date and time of the event.
     * @param to The end date and time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
        formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    }

    /**
     * Returns a string representation of the Event task, including its status, description,
     * start time, and end time.
     *
     * @return A string in the format "[E][status] description (from: start time to: end time)".
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from.format(formatter), to.format(formatter));
    }

    /**
     * Returns a string representation of the Event task in a format suitable for data storage.
     *
     * @return A string in the format "E | status | description | start time-end time".
     */
    @Override
    public String getDataFormat() {
        return "E" + super.getDataFormat() + " | " + from.format(formatter) + "-" + to.format(formatter);
    }
}
