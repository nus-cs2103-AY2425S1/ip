package duke.tasks;

import duke.Parser;
import duke.exceptions.InvalidEventException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime parsedFromDateTime;
    protected LocalDateTime parsedToDateTime;

    public Event(String description, String from, String to) throws InvalidEventException {
        super(description);

        parsedFromDateTime = null;
        parsedToDateTime = null;

        // Parse the 'from' field
        parsedFromDateTime = Parser.parseDateTime(from);
        if (parsedFromDateTime == null) {
            throw new InvalidEventException("Invalid 'from' date and time format.");
        }

        // Parse the 'to' field
        parsedToDateTime = Parser.parseDateTime(to);
        if (parsedToDateTime == null) {
            throw new InvalidEventException("Invalid 'to' date and time format.");
        }

        // Ensure that 'from' is before 'to'
        if (parsedFromDateTime.isAfter(parsedToDateTime)) {
            throw new InvalidEventException("'From' date and time must be before 'to' date and time.");
        }
    }

    // Getter method to format 'from' for display
    public String getFrom() {
        return parsedFromDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"));
    }

    // Getter method to format 'to' for display
    public String getTo() {
        return parsedToDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"));
    }

    @Override
    public boolean occurring(LocalDateTime taskDate) {
        return taskDate != null && taskDate.isAfter(this.parsedFromDateTime) && taskDate.isBefore(this.parsedToDateTime);
    }

    @Override
    public String toString() {
        return "[E]" + " [" + this.getStatusIcon() + "] " + super.toString() + " (from: " + this.getFrom() + " to: " + this.getTo() + ")";
    }
}
