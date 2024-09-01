import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Event extends Task {

    private LocalDate start;
    private LocalDate end;

    public Event(boolean status, String description, LocalDate start, LocalDate end) {
        super(status, description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toFileString() {
        return "D | " + this.getStatus() + " | " + this.getDescription() + " | " + this.start + " | " + this.end;
    }

    @Override
    public String toString() {
        return "[E][" + (this.getStatus() ? "X" : " ") + "] " +
                this.getDescription() + " (from: " + this.start.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " end: " + this.end.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }


}
