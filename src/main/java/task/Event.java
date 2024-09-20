package task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Event class extends Task and represents an event with a description, start date, and end date.
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructor for Event class.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns string format of event.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: "
            + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
            + " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
