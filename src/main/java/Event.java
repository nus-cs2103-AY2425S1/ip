import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * This class represents an Event Task
 * @author Gan Ren Yick (A0276246X)
 */

public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + from.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: "
                + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toData() {
        return "E/" + this.isDone + "/" + this.description + "/" + this.from + "/" + this.to;
    }
}
