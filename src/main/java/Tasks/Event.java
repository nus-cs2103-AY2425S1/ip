package Tasks;

import Tasks.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime startWhen;
    protected LocalDateTime endWhen;

    public Event(String description, LocalDateTime startWhen, LocalDateTime endWhen) {
        super(description);
        this.startWhen = startWhen;
        this.endWhen = endWhen;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return "E | " + this.getStatusIcon() + " | " + this.description + " (from: " + this.startWhen.format(formatter) + " to: " + this.endWhen.format(formatter) + ")";
    }

    @Override
    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "E | " + this.getStatusIcon() + " | " + this.description + " | " + this.startWhen.format(formatter) + " | " + this.endWhen.format(formatter);
    }
}
