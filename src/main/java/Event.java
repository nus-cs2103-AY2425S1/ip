import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event extends Task{
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toTextFormat() {
        return "E|" + (super.isTaskDone() ? "T" : "F") + "|" + super.getDescription() + "|" + this.from + "|" + this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)) + " to: " + to.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)) + ")";
    }
}
