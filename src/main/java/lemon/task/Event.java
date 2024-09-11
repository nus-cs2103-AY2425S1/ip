package lemon.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Represent the event tasks that includes date that the task will take place between
 * @author He Yiheng
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;
    /**
     * Constructor for the Event tasks
     * @param description description of the task
     * @param from date when the task starts
     * @param to date when the task ends
     * @param isDone whether the task is completed
     */
    public Event(String description, LocalDate from, LocalDate to, boolean isDone) {
        super(description, "event", isDone);

        this.from = from;
        this.to = to;
    }

    @Override
    public String toFileString() {
        return "E|" + isDone + "|" + description + "|" + from.toString() + "|" + to.toString() + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: "
                + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " to: "
                + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
