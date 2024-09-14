package star.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private String by;
    private LocalDateTime[] dates;

    public Event(String description, String by) {
        super(description);
        this.by = by;
    }

    public Event(String description, LocalDateTime... dates) {
        super(description);
        this.dates = dates;
    }

    public Event(String description, String tag, String by) {
        super(description, tag);
        this.by = by;
    }

    public Event(String description, String tag, LocalDateTime... dates) {
        super(description, tag);
        this.dates = dates;
    }

    @Override
    public String getOutput() {
        if (by == null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
            if (dates.length == 2) {
                return hasTag() ? String.format("E | %d | %s | %s | from %s to %s", isDone ? 1 : 0,
                        description, super.tag, dates[0].format(formatter), dates[1].format(formatter))
                        : String.format("E | %d | %s | from %s to %s", isDone ? 1 : 0,
                        description, dates[0].format(formatter), dates[1].format(formatter));
            } else if (dates.length == 1) {
                return hasTag() ? String.format("E | %d | %s | %s | at %s", isDone ? 1 : 0,
                        description, super.tag, dates[0].format(formatter))
                        : String.format("E | %d | %s | at %s", isDone ? 1 : 0,
                        description, dates[0].format(formatter));
            }
        }
        return hasTag() ? String.format("E | %d | %s | %s | %s", isDone ? 1 : 0, description, super.tag, by)
                : String.format("E | %d | %s | %s", isDone ? 1 : 0, description, by);
    }

    @Override
    public String toString() {
        if (by == null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
            if (dates.length == 2) {
                return hasTag() ? String.format("[E] %s (from: %s to: %s) #%s", super.toString(),
                        dates[0].format(formatter), dates[1].format(formatter), super.tag)
                        : String.format("[E] %s (from: %s to: %s)", super.toString(),
                        dates[0].format(formatter), dates[1].format(formatter));
            } else if (dates.length == 1) {
                return hasTag() ? String.format("[E] %s (at: %s) #%s", super.toString(),
                        dates[0].format(formatter), super.tag)
                        : String.format("[E] %s (at: %s)", super.toString(), dates[0].format(formatter));
            }
        }
        return hasTag() ? String.format("[E] %s (at: %s) #%s", super.toString(), by, super.tag)
                : String.format("[E] %s (at: %s)", super.toString(), by);
    }
}
