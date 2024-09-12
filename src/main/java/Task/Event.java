package Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
public class Event extends Task {
    private static final String format = "M/d/yyyy HHmm";
    protected String to;
    LocalDateTime dateTo;
    protected String from;
    LocalDateTime dateFrom;
    public Event(String description, String from, String to) {
        super(description);
        this.to = to;
        this.from = from;
        this.dateTo = LocalDateTime.parse(to, DateTimeFormatter.ofPattern(format));
        this.dateFrom = LocalDateTime.parse(from, DateTimeFormatter.ofPattern(format));
    }
    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
        this.dateTo = LocalDateTime.parse(to, DateTimeFormatter.ofPattern(format));
        this.dateFrom = LocalDateTime.parse(from, DateTimeFormatter.ofPattern(format));
    }
    /**
     * Returns a string representation of the Event task.
     *
     * @return a string representation of the Event task
     */
    @Override
    public String toString() {

        assert description != null : "empty description";
        assert dateFrom != null : "empty dateFrom";
        assert dateTo != null : "empty dateTo";

        return "[E]" + super.toString() + " (from: "
                + dateFrom.format(DateTimeFormatter.ofPattern("MMMM dd yyyy, ha", Locale.ENGLISH))
                        + " to: " +  dateTo.format(DateTimeFormatter.ofPattern("MMMM dd yyyy, ha", Locale.ENGLISH)) + ")";
    }
    /**
     * Returns a string representation of the Event task in a format to save in a file.
     *
     * @return a string representation of the Event task for saving
     */
    @Override
    public String save() {
        assert from != null : "empty from time";
        assert to != null : "empty to time";
        return "E | " + super.save() + " | " + from + " | " + to;
    }
}
