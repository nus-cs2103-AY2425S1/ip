package jeriel.task;
import jeriel.command.*;
import jeriel.util.*;import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Event extends Task {
    
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from);
        this.to = LocalDateTime.parse(to);
    }

    /**
     * Returns a string representation of this event in the save format.
     *
     * The format is "[E] description (from: MMM d yyyy h:mm a to: MMM d yyyy h:mm a)"
     *
     * @return the string representation of this event in the save format
     */
    @Override
    public String toSaveFormat() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a")) + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a")) + ")";
    }

    /**
     * Returns a string representation of this event.
     *
     * The format is "[E] description (from: yyyy-MM-dd'T'HH:mm to: yyyy-MM-dd'T'HH:mm)"
     *
     * @return the string representation of this event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
    
}
