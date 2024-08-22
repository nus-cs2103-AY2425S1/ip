package Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.to = to;
        this.from = from;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + this.formatTime(this.from) + " to: " + this.formatTime(this.to) + ")";
    }

    @Override
    public String printTask() {
        return "[E]" + super.toString() + " (from: " + this.from.format(DateTimeFormatter.ofPattern("dd MM yyyy HH:mm"))
                + " to: " + this.to.format(DateTimeFormatter.ofPattern("dd MM yyyy HH:mm")) + ")";
    }

    private String formatTime(LocalDateTime time) {
        return time.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
}
