import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class Event extends Task {
    private LocalDate startTime;
    private LocalDate endTime;

    public Event(String description, LocalDate startTime, LocalDate endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %s to: %s)",
                                                        startTime.format(DateTimeFormatter.ofPattern(Parser.DATE_OUTPUT_PATTERN)),
                                                        endTime.format(DateTimeFormatter.ofPattern(Parser.DATE_OUTPUT_PATTERN)));
    }
}
