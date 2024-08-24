import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class EventTask extends Task {
    protected String from;
    protected String to;

    public EventTask(String description, String from, String to) {
        super(description);
        try {
            LocalDate fromDate = LocalDate.parse(from);
            this.from = fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            this.from = from;
        }
        try {
            LocalDate toDate = LocalDate.parse(to);
            this.to = toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            this.to = to;
        }
    }

    public EventTask(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        try {
            LocalDate fromDate = LocalDate.parse(from);
            this.from = fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            this.from = from;
        }
        try {
            LocalDate toDate = LocalDate.parse(to);
            this.to = toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            this.to = to;
        }
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
