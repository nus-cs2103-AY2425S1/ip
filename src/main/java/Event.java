
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate start;
    protected LocalDate end;

    public Event(String description, LocalDate start, LocalDate end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)", super.getStatusIcons(), 
        super.description, this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
         this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String toFileString() {
        return String.format("E | %d | %s | %s-%s", this.done ? 1 : 0, this.description, 
          this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy")), 
            this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
