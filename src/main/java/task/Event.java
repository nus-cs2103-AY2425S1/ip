package task;

import java.time.LocalDate;
import java.time.Month;

/**
 * Represents a Task which has a starting date and a ending date.
 */
public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getLocalDate(from) + " to: " + getLocalDate(to) + ")";
    }

    private String getLocalDate(LocalDate date) {
        return Month.of(date.getMonthValue()).toString() + " " + date.getDayOfMonth() + " " + date.getYear();
    }
}
