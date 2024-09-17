package sam;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event item that extends the Item class.
 */
public class Event extends Item {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private LocalDate from;
    private LocalDate to;

    /**
     * Constructs an Event object with the specified name, start date, and end date.
     *
     * @param name the name of the event
     * @param from the start date of the event in the format "dd-MM-yyyy"
     * @param to the end date of the event in the format "dd-MM-yyyy"
     */
    public Event(String name, String from, String to) {
        super(name);
        this.from = LocalDate.parse(from, DATE_FORMAT);
        this.to = LocalDate.parse(to, DATE_FORMAT);
    }

    @Override
    public String toData() {
        return String.format(
            "E | %s | %s | %s\n", super.toData(), this.from.format(DATE_FORMAT), this.to.format(DATE_FORMAT));
    }

    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to: %s)",
        super.toString(), this.from.format(
            DateTimeFormatter.ofPattern("d MMMM yyyy")), this.to.format(
                DateTimeFormatter.ofPattern("d MMMM yyyy")));
    }
}
