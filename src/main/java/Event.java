import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String desc, LocalDateTime from, LocalDateTime to) {
        super(desc);
        this.from = from;
        this.to = to;
    }

    public String stringifyTask() {
        return String.format("E | %d | %s | %s %s", super.getStatus() ? 1 : 0,
                super.getDesc(),
                this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")),
                this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: "
                + this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                + " to: "
                + this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                + ")";
    }
}
