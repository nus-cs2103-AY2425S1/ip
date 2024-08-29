import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

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