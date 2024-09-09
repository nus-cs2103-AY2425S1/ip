package tick.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    /**
     * Constructs an event task with the specified description, start date, and end date.
     *
     * @param description Description of the event.
     * @param from Starting date of the event.
     * @param to Ending date of the event.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
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
                this.from.toString(), this.to.toString());
    }

    /**
     * Returns a string representation of the event task.
     * The format is "[E][status] description (from: start date to: end date)".
     *
     * @return A string representation of the event task.
     */
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                this.from.format(DateTimeFormatter.ofPattern("d MMM yyyy")),
                this.to.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
    }
}
