import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to)
            throws InvalidDateTimeRangeException {
        super("E", description);
        if (from.isAfter(to)) {
            throw new InvalidDateTimeRangeException();
        } else {
            this.from = from;
            this.to = to;
        }
    }

    @Override
    public String stringUI() {
        return super.stringUI() + " (from: "
                + this.from.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) + " to: "
                + this.to.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) + ")";
    }

    @Override
    public String stringStorage() {
        return super.stringStorage() + " | "
                + this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))+ " | "
                + this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

}
