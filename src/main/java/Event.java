import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    private final LocalDateTime from;
    private final LocalDateTime to;

    public Event(String name, String from, String to) {
        super(name);
        this.from = LocalDateTime.parse(from, inputFormatter);
        this.to = LocalDateTime.parse(to, inputFormatter);
    }

    public Event(boolean done, String name, String from, String to) {
        super(done, name);
        this.from = LocalDateTime.parse(from, inputFormatter);
        this.to = LocalDateTime.parse(to, inputFormatter);
    }

    @Override
    public String toText() {
        return String.format("E | %s | %s | %s",
                super.toText(),
                from.format(inputFormatter),
                to.format(inputFormatter));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + String.format(" (from: %s to: %s)", from.format(outputFormatter), to.format(outputFormatter));
    }
}
