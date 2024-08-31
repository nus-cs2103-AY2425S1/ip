package grok;

import java.time.LocalDate;
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

    public Event(String description, String start, String end, Boolean isDone) throws GrokInvalidUserInputException {
        super(description, isDone);
        try {
            this.start = LocalDate.parse(start);
            this.end = LocalDate.parse(end);
        } catch (DateTimeParseException e) {
            throw new GrokInvalidUserInputException("Invalid date format. Please declare your date in the ISO format yyyy-mm-dd.");
        }
    }

    @Override
    public String serialize() {
        return String.join(" | ", "E", super.serialize(), start.toString(), end.toString());
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }
}
