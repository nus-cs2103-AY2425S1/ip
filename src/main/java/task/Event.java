package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * An event task
 *
 * @author dwsc37
 */
public class Event extends Task {
    private final LocalDate start;
    private final LocalDate end;

    public Event(String description, LocalDate start, LocalDate end) {
        super(description);
        this.start = start;
        this.end = end;
    }
    
    public Event(String description, boolean isDone, LocalDate start, LocalDate end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ", to: " + end.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toData() {
        return "E | " + super.toData() + " | " + start + " | " + end;
    }
}

