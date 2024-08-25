import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");
        return "[E]" + super.toString() + "(from: " + from.format(outputFormat)
                + "HRS | to: " + to.format(outputFormat) + "HRS)";
    }

    @Override
    public String formatted() {
        DateTimeFormatter fileFormat = DateTimeFormatter.ofPattern("dd-MM-yy HHmm");
        return "E | " + super.formatted() + "| " + from.format(fileFormat)
                + " | " + to.format(fileFormat);
    }
}
