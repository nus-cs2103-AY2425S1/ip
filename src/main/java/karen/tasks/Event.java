package karen.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event class containing the Event's name, start and end LocalDateTimes
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructor to initialize the name, from and to attributes
     * @param name String representing the Event's name
     * @param from a <code>LocalDateTime</code> representing the start of the Event
     * @param to a <code>LocalDateTime</code> representing the end of the Event
     */
    public Event(String name, String from, String to) {
        super(name);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.from = LocalDateTime.parse(from, formatter);
        this.to = LocalDateTime.parse(to, formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mma");
        return "[E]" + super.toString() + String.format(" (from: %s to: %s)",
                this.from.format(formatter), this.to.format(formatter));
    }

    @Override
    public String toFileString() {
        int markedInt = this.isMarked() ? 1 : 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return String.format("E | %d | %s | %s | %s", markedInt, this.getName(),
                this.from.format(formatter), this.to.format(formatter));
    }
}
