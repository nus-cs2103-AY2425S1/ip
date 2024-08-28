import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime eventStartDate;
    protected LocalDateTime eventEndDate;
    private static final long serialVersionUID = 1L;

    public Event(String description, LocalDateTime eventStartDate, LocalDateTime eventEndDate) {
        super(description);
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.eventStartDate.format(defaultDateTimeFormatter) + ")" + " (to" +
                ": " + this.eventEndDate.format(defaultDateTimeFormatter) + ")";
    }
}
