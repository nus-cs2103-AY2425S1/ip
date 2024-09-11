package MeowMeow;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task which has start date and end date
 */
class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, String from, String to) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.from = LocalDate.parse(from, formatter);
        this.to = LocalDate.parse(to, formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[E]" + super.toString() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }

    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " | " + to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}