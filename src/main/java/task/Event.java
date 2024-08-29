package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Keeps track of the event task that consist of description, start date, end date
 */
public class Event extends Task {
    private LocalDate start;
    private LocalDate end;
    public Event(String description, String start, String end) {
        super(description);
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }

    /**
     * Outputs event object into a String
     * @return Event object as a string
     */
    @Override
    public String toString() {
        String starting = this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String ending = this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString() + " (from: " + starting + " to: " + ending + ")";
    }

    /**
     * Formats event object into a string to be saved in.
     * @return String to be saved.
     */
    @Override
    public String toSave() {
        return "E" + " | " + (this.isDone ? 1 : 0) + " | " + this.description + " | " + this.start + " | " + this.end;
    }
}
