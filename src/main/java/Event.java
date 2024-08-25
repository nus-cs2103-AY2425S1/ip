import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    LocalDateTime from;
    LocalDateTime to;

    public Event(String name, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(name, isDone);
        this.from = from;
        this.to = to;
    }

    public Event(String name, LocalDateTime from, LocalDateTime to) {
        this(name, from, to, false);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("LLL dd yyyy HH:mm");
        String str = String.format("[E]%s (from: %s to: %s)", super.toString(),
                formatter.format(this.from), formatter.format(this.to));
        return str;
    }

    @Override
    public String encode() {
        return "E|" + super.encode() + "|" + this.from + "|" + this.to;
    }
}
