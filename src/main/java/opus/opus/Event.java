package opus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDateTime fromDateTime;
    private LocalDateTime toDateTime;
    private String fromString;
    private String toString;

    public Event(String description, String from, String to) {
        super(description);
        try {
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            this.fromDateTime = LocalDateTime.parse(from, inputFormat);
            this.toDateTime = LocalDateTime.parse(to, inputFormat);
        } catch (DateTimeParseException e) {
            this.fromString = from;
            this.toString = to;
        }
    }

    private String getFrom() {
        if (fromDateTime != null) {
            DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
            return fromDateTime.format(outputFormat);
        } else {
            return fromString;
        }
    }

    private String getTo() {
        if (toDateTime != null) {
            DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
            return toDateTime.format(outputFormat);
        } else {
            return toString;
        }
    }

    @Override
    public String toSaveFormat() {
        return "E|" + (isDone ? "1" : "0") + "|" + description + "|" + getFrom() + "|" + getTo();
    }

    public static Task fromFileFormat(String fullLine) {
        String[] parts = fullLine.split("\\|");
        Event event = new Event(parts[2], parts[3], parts[4]);
        if (parts[1].equals("1")) {
            event.markAsDone();
        }
        return event;
    }

    @Override
    public String toString() {
        return "[E]" + (isDone ? "[X] " : "[ ] ") + description + " (from: " + getFrom() + " to: " + getTo() + ")";
    }
}
