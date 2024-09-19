package jeriel.task;

import java.time.LocalDateTime;

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
     * @return the string representation of this event in the save format
     */
    @Override
    public String toSaveFormat() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns a string representation of this event.
     *
     * The format expected is "[E][ ] description (from: yyyy-MM-dd'T'HH:mm to: yyyy-MM-dd'T'HH:mm)".
     * 
     * @return the string representation of this event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.toString() + " to: " + to.toString() + ")";
    }
}
