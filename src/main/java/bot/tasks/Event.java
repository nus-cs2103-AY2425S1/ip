package bot.tasks;

import java.time.LocalDate;

import bot.enums.TaskSymbol;

/**
 * Represents an event task.
 *
 * @author mongj
 */
public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    /**
     * Creates a new <code>Event</code> object.
     *
     * @param description of the event.
     * @param from when the event begins.
     * @param to when the event ends.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Creates a new <code>Event</code> object.
     *
     * @param description of the event.
     * @param isDone indicating if the task has been marked completed.
     * @param from when the event begins.
     * @param to when the event ends.
     */
    public Event(String description, Boolean isDone, LocalDate from, LocalDate to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toData() {
        return TaskSymbol.EVENT.name + " | " + super.toData() + " | " + from + " | " + to;
    }
}
