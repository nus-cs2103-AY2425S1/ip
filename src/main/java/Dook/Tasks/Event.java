package Dook.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String fileFormatted() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "E | " + super.fileFormatted() + " | " + this.start.format(formatter) + " | " + this.end.format(formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");
        return "[E]" + super.toString() + " (From: " + this.start.format(formatter) + " To: " + this.end.format(formatter) + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Event)) {
            return false;
        } else if (o == this) {
            return true;
        }

        Event e = (Event) o;

        return e.description.equals(this.description) && e.start.equals(this.start) && e.end.equals(this.end);
    }
}
