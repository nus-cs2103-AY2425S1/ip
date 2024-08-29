import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate start;
    private LocalDate end;
    public Event(String task, LocalDate start, LocalDate end) {
        super(task);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to: %s)",
                super.toString(),
                start.format(DateTimeFormatter.ofPattern("MMM dd yyyy")),
                end.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
    @Override
    public String toStorageString() {
        return String.format("E | %s | %s | %s",this.taskString, this.start, this.end);
    }
}
