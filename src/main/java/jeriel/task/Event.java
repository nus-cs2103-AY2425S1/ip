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

    @Override
    public String toSaveFormat() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a")) + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a")) + ")";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
    
}
