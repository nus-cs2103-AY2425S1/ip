
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    // Using a date-only format
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from, DATE_FORMATTER);
        this.to = LocalDate.parse(to, DATE_FORMATTER);
    }

    @Override
    public String toSaveFormat() {
        return "E | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | " + from.format(DATE_FORMATTER) + " | " + to.format(DATE_FORMATTER);
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
