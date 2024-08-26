import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class Event extends Task {

    protected LocalDate fromDuration;
    protected LocalDate toDuration;

    public Event(String description, LocalDate fromDuration, LocalDate toDuration) {
        super(description);
        this.toDuration = toDuration;
        this.fromDuration = fromDuration;
        Task.taskCount++;
    }

    @Override
    public String toDataString() {
        return "E | " + super.toDataString() + " | " + fromDuration + " | " + toDuration;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.fromDuration + " to: " + this.toDuration + ")";
    }
}
