import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{

    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String description, String from, String to) throws DateTimeFormatException {
        super(description);
        try {
            this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("d/M/yyyy H:mm"));
            this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("d/M/yyyy H:mm"));
        } catch (DateTimeParseException e) {
            throw new DateTimeFormatException();
        }
    }

    @Override
    public String getCsvFormat() {
        return "E,"+ super.getCsvFormat() +
                "," + from.format(DateTimeFormatter.ofPattern("d/M/yyyy H:mm")) +
                "," + to.format(DateTimeFormatter.ofPattern("d/M/yyyy H:mm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("d/M/yyyy H:mm")) +
                " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy H:mm")) + ")";
    }
}
