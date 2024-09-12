package nayana.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific start date and end date.
 */
public class Event extends Task {

    private LocalDate start;
    private LocalDate end;

    /**
     * Constructs an event task with the given description, start date, and end date.
     *
     * @param description The description of the task.
     * @param start The start date of the event.
     * @param end The end date of the event.
     */
    public Event(String description, LocalDate start, LocalDate end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the type of the task as a string.
     *
     * @return A string "E " representing the event type.
     */
    @Override
    public String getType() {
        return "E ";
    }

    /**
     * Returns the event dates as a string.
     *
     * @return A string representing the start and end dates of the event.
     */
    @Override
    public String getDates() {
        return "| " + start.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
              + " - " + end.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return A string in the format "[E] description (from: start to: end)".
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
              + " (from: " + start.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
              + " to: " + end.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    public LocalDate getEndDate() {
        return this.end;
    }

}
