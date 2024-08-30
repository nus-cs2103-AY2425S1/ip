package agave;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm");

    public Event(String description, String start, String end) {
        super(description);
        this.start = LocalDateTime.parse(start, INPUT_FORMAT);
        this.end = LocalDateTime.parse(end, INPUT_FORMAT);
    }

    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String getCorrectFormat() {
        return String.format("%s | %s | %s", getDescription(), start.format(INPUT_FORMAT), end.format(INPUT_FORMAT));
    }

    @Override
    public String toString() {
        return " [E] " + super.toString() + " (from: " + start.format(OUTPUT_FORMAT) + " to: "
                + end.format(OUTPUT_FORMAT) + ")";
    }
}
