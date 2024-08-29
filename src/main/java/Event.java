import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    public Event(String description) throws UnknownTimeException {
        super(description.substring(0, description.indexOf("/from") - 1));
        this.from = this.stringToDate(description.substring(description.indexOf("/from") + 6,
                description.indexOf("/to") - 1));
        this.to = this.stringToDate(description.substring(description.indexOf("/to") + 4));
    }

    public Event(String description, String from, String to) {
        super(description);
        this.from = this.stringToDate(from);
        this.to = this.stringToDate(to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.dateToString(this.from)
                + " to: " + this.dateToString(this.to) + ")";
    }

    public String fileString() {
        return super.fileString() + " | " + this.from + " | " + this.to;
    }

    private LocalDate stringToDate(String s) {
        return LocalDate.parse(s);
    }

    private String dateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String getDay() {
        return this.from.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault())
                + " to " + this.to.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());
    }
}
