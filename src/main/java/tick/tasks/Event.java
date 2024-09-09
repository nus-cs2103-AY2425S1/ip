package tick.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class Event extends Task {
    private LocalDate dateFrom;
    private LocalDate dateTo;

    /**
     * Constructs an event task with the specified description, start date, and end date.
     *
     * @param description Description of the event.
     * @param dateFrom Starting date of the event.
     * @param dateTo Ending date of the event.
     */
    public Event(String description, LocalDate dateFrom, LocalDate dateTo) {
        super(description);
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    /**
     * Converts the event task to a string format suitable for file storage.
     * The format is "E | status | description | start date | end date".
     *
     * @return A string representation of the event task for file storage.
     */
    @Override
    public String toStorageFormat() {
        return String.format("E | %s | %s | %s | %s", super.getStatus() ? "1" : "0",
                super.getDescription(),
                this.dateFrom.toString(), this.dateTo.toString());
    }

    /**
     * Returns a string representation of the event task.
     * The format is "[E][status] description (from: start date to: end date)".
     *
     * @return A string representation of the event task.
     */
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                this.dateFrom.format(DateTimeFormatter.ofPattern("d MMM yyyy")),
                this.dateTo.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
    }
}
