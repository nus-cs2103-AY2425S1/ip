package genji.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A class that deals with event tasks
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructor of event class
     * @param name Task's name
     * @param from Period start time
     * @param to Period end time
     */
    public Event(String name, LocalDateTime from, LocalDateTime to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    /**
     * Gets the event period
     * @return LocalDateTime of the deadline
     */
    public LocalDateTime getDate() {
        return this.from;
    }

    /**
     * Formats the event task into strings used for saving to file
     * @return The String to be saved
     */
    @Override
    public String toListString() {
        return "E" + super.toListString() + " | " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) +
                " to " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

    /**
     * Formats the event task into strings used for display
     * @return The String to be displayed
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) +
                " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
