package pixy.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

// Class for Tasks which are events
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String from, String to) {
        super(description);
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");

        try {
            this.from = LocalDateTime.parse(from.trim(), inputFormatter);
            this.to = LocalDateTime.parse(to.trim(), inputFormatter);
        } catch (DateTimeParseException e) {
            try {
                this.from = LocalDateTime.parse(from.trim(), displayFormatter);
                this.to = LocalDateTime.parse(to.trim(), displayFormatter);
            } catch (DateTimeParseException ex) {
                throw new IllegalArgumentException("Invalid date format: " + from + " or " + to);
            }
        }
    }

    public String getFrom() {
        return from.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"));
    }

    public String getTo() {
        return to.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + getFrom() + " to: " + getTo() + ")";
    }
}
