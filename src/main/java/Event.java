import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    public Event(String description, String from, String to) {
        super(description);
        try {
            this.from = LocalDate.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            this.to = LocalDate.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use 'yyyy-MM-dd'.");
        }
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String additionalDescDetailsToFileFormat() {
        return " | " + from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) +
                " | " + to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) +
                " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
