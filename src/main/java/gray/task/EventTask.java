package gray.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A task that represents an event, has a start and end datetime.
 */
public class EventTask extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Constructs an event that represents an event.
     * @param description
     * @param start
     * @param end
     */
    public EventTask(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return String.format(
                "[E]%s (from: %s to: %s)",
                super.toString(),
                start.format(formatter),
                end.format(formatter));
    }
}
