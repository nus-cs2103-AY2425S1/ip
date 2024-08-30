package garfield.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;
    private DateTimeFormatter saveFormatter;
    private DateTimeFormatter uiFormatter;

    public Event(String eventDescription, LocalDateTime from, LocalDateTime to) {
        super(eventDescription);
        this.from = from;
        this.to = to;
        this.saveFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.uiFormatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mma");
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.format(uiFormatter) + " to: " + this.to.format(uiFormatter) + ")";
    }

    @Override
    public String toSaveRepresentation() {
        return "E | " + super.toSaveRepresentation() + " | " + this.from.format(saveFormatter) + " | " + this.to.format(saveFormatter);
    }
}
