import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String formatString() {
        return String.format("E | %s | %s | %s", super.formatString(), this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy")), this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to: %s)", super.toString(), this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy")), this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
