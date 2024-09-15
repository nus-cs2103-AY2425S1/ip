package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task that occurs over a period of time.
 * This class extends the Task class to include a start and end date.
 */
public class Event extends Task {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM uuuu");

    /**
     * Constructs an Event with the specified name, start date, and end date.
     *
     * @param task the name of the event
     * @param startDate the start date of the event
     * @param endDate the end date of the event
     */
    public Event(String task, LocalDate startDate, LocalDate endDate) {
        super(task);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns a string representation of the event, including its name,
     * completion status, and the start and end dates.
     * The format is "[ ] taskName | Event from startDate to endDate".
     *
     * @return a string representation of the event
     */
    @Override
    public String toString() {
        return String.format("%s | Event from %s to %s", super.toString(),
                outputFormatter.format(this.startDate), outputFormatter.format(this.endDate));
    }

    /**
     * Returns the start date of the event.
     *
     * @return the start date of the event
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Returns the end date of the event.
     *
     * @return the end date of the event
     */
    public LocalDate getEndDate() {
        return endDate;
    }
}
