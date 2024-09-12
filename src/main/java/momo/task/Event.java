package momo.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class represents a event task object that has been created,
 * storing the from and to dates as a {@code LocalDate} object and providing functionality
 * for converting the object to a file string to store in the storage file
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    public Event(String task, LocalDate from, LocalDate to, boolean isCompleted) {
        super(task, isCompleted);
        this.from = from;
        this.to = to;
    }

    public String toFileString() {
        return "E|" + super.toFileString() + "|" + from + "|" + to;
    }

    public String toString() {
        return "[E]" + super.toString() + "(" + from.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

}
