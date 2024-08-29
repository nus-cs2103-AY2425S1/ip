package reminderebot.task;

import java.time.LocalDateTime;

/**
 * Event represents a Task with a duration from a datetime to a datetime.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Create an Event from the user input.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    };

    /**
     * Creates an entry to a file from an Event.
     * @return string representing the Event
     */
    @Override
    public String toFile() {
        return "E|" + getStatusIcon() + "|" + description + "|" +
                from + "|" + to;
    }

    /**
     * Create an Event from the text in text file.
     * @return
     */
    @Override
    public Event createFromFile(String file) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the String representing the Event to be displayed in tasklist.
     * @return string representing Event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(formatter) +
                " to: " + to.format(formatter) + ")";
    }
}
