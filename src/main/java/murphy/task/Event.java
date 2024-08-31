package murphy.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import murphy.MurphyException;

public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;
    public Event(String description, String from, String to) throws MurphyException {
        super(description);
        String fromTrimmed = from.trim();
        String toTrimmed = to.trim();
        if (fromTrimmed.isEmpty()) {
            throw new MurphyException("Event from date cannot be empty!");
        }
        if (toTrimmed.isEmpty()) {
            throw new MurphyException("Event to date cannot be empty!");
        }
        try {
            this.from = LocalDate.parse(fromTrimmed);
            this.to = LocalDate.parse(toTrimmed);
        } catch (DateTimeParseException e) {
            throw new MurphyException("Dates should be in the format yyyy-mm-dd.");
        }
    }

    public Event(String description, boolean isDone, String from, String to) throws MurphyException{
        super(description, isDone);
        String fromTrimmed = from.trim();
        String toTrimmed = to.trim();
        if (fromTrimmed.isEmpty()) {
            throw new MurphyException("Event from date cannot be empty!");
        }
        if (toTrimmed.isEmpty()) {
            throw new MurphyException("Event to date cannot be empty!");
        }
        try {
            this.from = LocalDate.parse(fromTrimmed);
            this.to = LocalDate.parse(toTrimmed);
        } catch (DateTimeParseException e) {
            throw new MurphyException("Dates should be in the format yyyy-mm-dd.");
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        return "[E]" + super.toString() + " (from: " + this.from.format(formatter)
                + " to: " + this.to.format(formatter) + ")";
    }

    @Override
    public String toSaveString() {
        return "E|" + super.toSaveString() + "|" + this.from + "|" + this.to;
    }
}
