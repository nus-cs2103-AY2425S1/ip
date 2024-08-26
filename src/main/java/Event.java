import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Event extends Task {
    protected String from;
    protected String to;
    protected LocalDateTime fromDate;
    protected LocalDateTime toDate;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, LocalDateTime fromDate, LocalDateTime toDate) {
        super(description);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Event(String description, LocalDateTime fromDate, String to) {
        super(description);
        this.fromDate = fromDate;
        this.to = to;
    }

    public Event(String description, String from, LocalDateTime toDate) {
        super(description);
        this.from = from;
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        String formattedFromDate = (this.fromDate != null)
                ? this.fromDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                : this.from;
        String formattedToDate = (this.toDate != null)
                ? this.toDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                : this.to;
        return "[E]" + super.toString() + " (from: " + formattedFromDate + " to: " + formattedToDate + ")";
    }

    public List<String> getTaskDetails() {
        String formattedFromDate = (this.fromDate != null)
                ? this.fromDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                : this.from;
        String formattedToDate = (this.toDate != null)
                ? this.toDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                : this.to;
        return List.of("E", getStatus(), description, formattedFromDate, formattedToDate);
    }
}
