package ollie.task;

import ollie.exception.OllieException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Event extends Task {

    private LocalDate from;
    private LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) throws OllieException {
        super(description);

        if (!from.isBefore(to)) {
            throw new OllieException("/from's date must be before /to's date!");
        }
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    public String getFormattedString() {
        return "E | " + super.getFormattedString() + " | " + this.from + " | " + this.to;
    }
}