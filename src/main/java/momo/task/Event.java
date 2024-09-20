package momo.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class represents a event task object that has been created,
 * storing the from and to dates as a {@code LocalDate} object and providing functionality
 * for converting the object to a file string to store in the storage file.
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructor which creates a new Event object.
     * @param task User input of the event description.
     * @param from Date the event begins, formatted as a LocalDate object.
     * @param to Date the event ends, formatted as a LocalDate object.
     * @param isCompleted The completion status of the Event.
     */
    public Event(String task, LocalDate from, LocalDate to, boolean isCompleted) {
        super(task, isCompleted);
        this.from = from;
        this.to = to;
    }

    public String toFileString() {
        return "E|" + super.toFileString() + "|" + from + "|" + to;
    }

    /**
     * Returns a string containing the event's description.
     * @return String value containing string of the event's description.
     */
    public String toString() {
        return "[E]" + super.toString() + "(" + from.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to "
                + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

}
