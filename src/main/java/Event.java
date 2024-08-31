import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDate start;
    protected LocalDate end;

    public Event(String description, String start, String end) throws GrokInvalidUserInputException {
        super(description);
        try {
            this.start = LocalDate.parse(start);
            this.end = LocalDate.parse(end);
        } catch (DateTimeParseException e) {
            throw new GrokInvalidUserInputException("Invalid date format. Please declare your date in the ISO format yyyy-mm-dd.");
        }
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + "(from: " + start.format(DateTimeFormatter.ofPattern("dd MM yyyy"))
                + " to: " + end.format(DateTimeFormatter.ofPattern("dd MM yyyy")) + ")";
    }
}
