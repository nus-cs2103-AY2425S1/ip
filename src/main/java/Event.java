import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDateTime startDateTime;
    protected LocalDateTime endDateTime;
    protected static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    public Event(String description, String startDateTime, String endDateTime) {
        super(description);
        try {
            this.startDateTime = LocalDateTime.parse(startDateTime.trim(), formatter);
            this.endDateTime = LocalDateTime.parse(endDateTime.trim(), formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use 'd/M/yyyy HHmm'.");
            throw e;  // Re-throw the exception after logging it
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")) +
                " to: " + endDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")) + ")";
    }
}