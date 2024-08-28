package alice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public Event(String description, LocalDateTime start, LocalDateTime end, boolean isDone) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String saveString() {
        DateTimeFormatter saveFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        String formattedStart = (start != null) ? start.format(saveFormatter) : "N/A";
        String formattedEnd = (end != null) ? end.format(saveFormatter) : "N/A";
        return "E" + super.saveString() + " | " + formattedStart + " | " + formattedEnd;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        String formattedStart = (start != null) ? start.format(formatter)
                : "Fail to set start time, check time format: dd-MM-yyyy HHmm";
        String formattedEnd = (end != null) ? end.format(formatter)
                : "Fail to set end time, check time format: dd-MM-yyyy HHmm";
        return "[E]" + super.toString() +
                " (from: " + formattedStart + " to: " + formattedEnd + ")";
    }
}
