package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    @Override
    public String writeTask() {
        return super.writeTask() + "," + this.from + "," + this.to;
    }
    
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(),
                this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy")),
                this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
