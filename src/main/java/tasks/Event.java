package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    /**
     * Constructor for an Event
     * 
     * @param description
     * @param from
     * @param to
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " to: "
                + this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
