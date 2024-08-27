import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
public class Event extends Task{
    protected String to;
    LocalDateTime dateTo;
    protected String from;
    LocalDateTime dateFrom;
    public Event(String description, String from, String to) {
        super(description);
        this.to = to;
        this.from = from;
        this.dateTo = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("M/d/yyyy HHmm"));
        this.dateFrom = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("M/d/yyyy HHmm"));
    }
    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
        this.dateTo = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("M/d/yyyy HHmm"));
        this.dateFrom = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("M/d/yyyy HHmm"));
    }
    @Override
    public String toString() {
        //return "[E]" + super.toString() + " (from: " + from + " to: " +  to + ")";
        return "[E]" + super.toString() + " (from: "
                + dateFrom.format(DateTimeFormatter.ofPattern("MMMM dd yyyy, ha", Locale.ENGLISH))
                        + " to: " +  dateTo.format(DateTimeFormatter.ofPattern("MMMM dd yyyy, ha", Locale.ENGLISH)) + ")";
    }

    @Override
    public String save() {
        return "E | " + super.save() + " | " + from + " | " + to;
    }
}
