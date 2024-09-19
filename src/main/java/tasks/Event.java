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

    /**
     * Converts the event task into a string format suitable for saving to a file or database.
     * The format is "e,taskName,completionStatus,startDate,endDate", where completionStatus is "y" if the task is done,
     * and "n" if it is not done.
     *
     * @return a string representation of the event task in the format "e,taskName,completionStatus,startDate,endDate"
     */
    @Override
    public String toSaveFormat() {
        return String.format("e,%s,%s,%s", super.toSaveFormat(), this.startDate, this.endDate);
    }
}
