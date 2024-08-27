import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private final LocalDateTime eventFrom;
    private final LocalDateTime eventTo;

    public Event(String description, LocalDateTime eventFrom, LocalDateTime eventTo) {
        super(description);
        this.eventFrom = eventFrom;
        this.eventTo = eventTo;
    }

    @Override
    public String toString() {
        return "[E][" + (this.getDone() ? "X" : " ") + "] " + this.getDescription() +
                (eventFrom != null ? " (from: " + eventFrom.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) : "") +
                (eventTo != null ? " to: " + eventTo.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")" : "");
    }

    @Override
    public String toFileFormat() {
        return "E | " + (this.getDone() ? "1" : "0") + " | " + this.getDescription() +
                (eventFrom != null ? " | " + eventFrom.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")) : "") +
                (eventTo != null ? " | " + eventTo.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")) : "");
    }

    public static Event parseTask(String taskData) {
        String[] parts = taskData.split(" \\| ");
        String description = parts[2];
        LocalDateTime from = null;
        LocalDateTime to = null;
        if (parts.length > 3) {
            try {
                from = LocalDateTime.parse(parts[3], DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
                if (parts.length > 4) {
                    to = LocalDateTime.parse(parts[4], DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
                }
            } catch (DateTimeParseException e) {
                System.out.println("Warning: There is no date format provided.");
            }
        }
        Event event = new Event(description, from, to);
        if (parts[1].trim().equals("1")) {
            event.markDone();
        }
        return event;
    }
}
