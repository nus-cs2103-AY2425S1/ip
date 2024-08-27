package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected LocalDate start;
    protected LocalDate end;

    public Event(String description, String start, String end) throws EventException {
        super(description);

        // Parse datetime
        try {
            this.start = LocalDate.parse(start);

        } catch (DateTimeParseException e) {
            throw new EventException("Cannot parse start date");
        }

        try {
            this.end = LocalDate.parse(end);

        } catch (DateTimeParseException e) {
            throw new EventException("Cannot parse end date");
        }

    }

    /**
     * Returns a formatted string representation
     *
     * @return data format
     */
    @Override
    public String toDataFormat() {
        return "event | " + super.toDataFormat() + " | "
                + start + " | " + end;
    }

    /**
     * Returns a formatted message with status icon
     * and description
     *
     * @return display message
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + start.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + " to: "
                + end.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
