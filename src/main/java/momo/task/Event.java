package momo.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
