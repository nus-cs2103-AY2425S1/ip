import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a type of Task that has a start and end time.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs an Event task using the given description, from and to date.
     * @param description The String description of the Event.
     * @param from The starting time of the Event.
     * @param to The ending time of the Event.
     */
    public Event(String description, String from, String to) {
        super(description);

        String startDate = from.trim();
        String endDate = to.trim();
        try {
            LocalDate date = LocalDate.parse(startDate);
            startDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            LocalDate date2 = LocalDate.parse(endDate);
            endDate = date2.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch(DateTimeException ignored) {

        }
        this.from = startDate;
        this.to = endDate;
    }

    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from " + from + " to " + to + ")";
    }
}
