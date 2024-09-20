package sadcat.tasks;

import java.time.LocalDateTime;

/**
 * Represents an event task with a start and end time.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs a new Event task.
     *
     * @param description The description of the event
     * @param from The start time of the event
     * @param to The end time of the event
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + from.format(printFormatter) + " to: "
                + to.format(printFormatter) + ")";
    }

    @Override
    public String saveFormat() {
        return "E | " + super.saveFormat() + " | " + from.format(saveFormatter) + " | " + to.format(saveFormatter);
    }
}
