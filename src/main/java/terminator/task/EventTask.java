package terminator.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Concrete class representing a 'Event' item in the task list.
 */
public class EventTask extends Task {

    private final LocalDateTime fromDate;

    private final LocalDateTime toDate;

    /**
     * Creates a new instance of an EventTask with a given description, start date and end date.
     *
     * @param description The description of the event.
     * @param fromDate The start date and time.
     * @param toDate The end date and time.
     */
    public EventTask(String description, LocalDateTime fromDate, LocalDateTime toDate) {
        super(description, TaskType.EVENT);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /**
     * Returns the string format of the task.
     *
     * @example {@code "[E][ ] destroy aliens from: Jan 1 2000 09:00 to: Jan 2 2000 09:00"}
     * @return The EventTask formatted as a string.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
        String fromDateString = fromDate.format(formatter);
        String toDateString = toDate.format(formatter);
        return super.toString() + " from: " + fromDateString + " to: " + toDateString;
    }

    /**
     * Returns the data representation of the task, plus its fromDate, toDate, and description.
     * @example {@code "T 1 2000-01-01+0900 2000-01-02+0900 destroy aliens"}
     */
    @Override
    public String getDataRepresentation() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd+HH:mm");
        return super.getDataRepresentation()
                + this.fromDate.format(formatter) + " "
                + this.toDate.format(formatter) + " "
                + this.description;
    }
}
