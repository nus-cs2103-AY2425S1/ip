package ratchet.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event task.
 */
public class EventTask extends Task {
    private final LocalDate from;
    private final LocalDate to;

    /**
     * Constructor for new Event task.
     *
     * @param description The description of the task.
     * @param from        The start of the task.
     * @param to          The end of the task.
     */
    public EventTask(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructor for loading Event task.
     *
     * @param description The description of the task.
     * @param isDone      The status of the task.
     * @param from        The start of the task.
     * @param to          The end of the task.
     */
    public EventTask(String description, boolean isDone, LocalDate from, LocalDate to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toSave() {
        return "E|" + super.toSave() + "|" + from + "|" + to;
    }
}
