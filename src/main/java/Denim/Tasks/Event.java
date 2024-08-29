package denim.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private final static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");

    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.format(dateFormatter)
                + " to: " + to.format(dateFormatter) + ")";
    }

    @Override
    public String toSimplifiedString() {
        String formattedString = String.format("E | %d | %s | %s | %s\n", super.getIsDone() ? 1 : 0,
                super.getDescription(), from.format(dateFormatter), to.format(dateFormatter));
        return formattedString;
    }
}

