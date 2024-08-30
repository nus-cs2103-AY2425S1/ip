import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    Event(String name, LocalDateTime from, LocalDateTime to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    Event(boolean isDone, String name, LocalDateTime from, LocalDateTime to) {
        super(isDone, name);
        this.from = from;
        this.to = to;
    }

    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return String.format(
                "[E]%s (from: %s to: %s)",
                super.toString(), this.from.format(dtf), this.to.format(dtf));
    }

    public String convertToTxt() {
        return String.format("%s,%s,%s,%s","E", super.convertToTxt(), this.from, this.to);
    }
}