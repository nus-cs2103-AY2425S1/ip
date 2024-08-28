import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm", Locale.ENGLISH))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm", Locale.ENGLISH)) + ")";
    }

    @Override
    public  String toFileRecord() {
        Integer status = isDone ? 1 : 0;
        return "E" + " | " + status + " | " + this.description
                + " | " + this.from + " | " + this.to;
    }

    @Override
    public String getStatus() {
        return "[E]" + "[" + super.getStatusIcon() + "]" + " " + super.toString()
                + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm", Locale.ENGLISH))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm", Locale.ENGLISH)) + ")";
    }
}
