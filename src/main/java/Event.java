import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDateTime startDate;
    protected LocalDateTime endDate;
    public Event(String description, String startDate, String endDate) throws BuzzException {
        super(description);
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            this.startDate = LocalDateTime.parse(startDate, dateTimeFormatter);
            this.endDate = LocalDateTime.parse(endDate, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new BuzzException("WRONG!!! The date and time format should be d/M/yyyy HHmm (e.g., 5/10/2024 0500).");
        }
    }
    public String formatStart() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");
        return this.startDate.format(dateTimeFormatter);
    }
    public String formatEnd() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");
        return this.endDate.format(dateTimeFormatter);
    }
    public String toFileFormat() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + this.startDate.format(dateTimeFormatter) + " | " + this.endDate.format(dateTimeFormatter);
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + formatStart() + " to: " + formatEnd() + ")";
    }
}
