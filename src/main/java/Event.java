import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Event class represents a task that occurs during a specific period of time
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an event task with the given description and period of time
     *
     * @param description The description of the event
     * @param from The data and time the event starts
     * @param to The date and time the event ends
     * @throws SageException if the description is empty or if the time format is inavlid
     */
    public Event(String description, String from, String to) throws SageException {
        super(description);
        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            this.from = LocalDateTime.parse(from, inputFormatter);
            this.to = LocalDateTime.parse(to, inputFormatter);
        } catch (DateTimeParseException e) {
            throw new SageException("Invalid date format! Please use the format 'yyyy-mm-dd HHmm'.");
        }
        if (description.isEmpty()) {
            throw new SageException("What time is your event?? :o");
        }
    }

    public LocalDateTime getFrom() {
        return this.from;
    }

    public LocalDateTime getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a")) + " to: " + to.format(DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a")) + ")";
    }

    @Override
    public String toFileFormat() {
        DateTimeFormatter fileFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from.format(fileFormatter) + " | " + to.format(fileFormatter);
    }
}
