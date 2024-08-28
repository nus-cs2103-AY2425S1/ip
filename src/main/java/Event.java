import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {    private LocalDateTime from;
    private LocalDateTime to;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
    private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    public Event(String description, boolean isDone, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, inputFormatter);
        this.to = LocalDateTime.parse(to, inputFormatter);
        if (isDone) markAsDone();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }

    @Override
    public String toFileFormat() {
        return "E | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | " +
                from.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + " | " +
                to.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}
