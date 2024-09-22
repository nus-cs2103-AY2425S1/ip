package panorama.task;

import java.time.LocalDate;

import panorama.DateParser;

/**
 * Represents an event task with a name and a time range.
 * An event task has a start date and time and an end date and time.
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    /**
     * Constructs an {@code Event} task with the specified name, start date, and end date.
     *
     * @param name The name or description of the event task.
     * @param from The start date and time of the event.
     * @param to The end date and time of the event.
     */
    public Event(String name, LocalDate from, LocalDate to) {
        super(name, 'E');
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the event task in a format suitable for file storage.
     * The format includes the task details, start date, and end date, separated by '|'.
     *
     * @return A formatted string for writing the event task to a file.
     */
    @Override
    public String toFileString() {
        String dateFrom = DateParser.formatForHardDisk(from);
        String dateTo = DateParser.formatForHardDisk(to);
        return super.toFileString() + "|" + dateFrom + "|" + dateTo;
    }

    /**
     * Returns a string representation of the event task for displaying in the user interface.
     * The format includes the task details, start date, and end date.
     *
     * @return A formatted string representing the event task.
     */
    @Override
    public String toString() {
        String dateFrom = DateParser.format(from);
        String dateTo = DateParser.format(to);
        return super.toString() + " (from: " + dateFrom + " to: " + dateTo + ")";
    }
}

