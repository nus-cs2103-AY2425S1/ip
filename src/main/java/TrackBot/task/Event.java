package TrackBot.task;

import TrackBot.task.Task;
import TrackBot.ui.Parser;

/**
 * Event task with a start and end time.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs an Event task with the specified description, start time and end time.
     *
     * @param desc The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String desc, String from, String to) {
        super(desc);
        this.from = from;
        this.to = to;
        this.from = Parser.checkDateFormat(from);
        this.to = Parser.checkDateFormat(to);
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String toStorageFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + desc + " | " + from + " | " + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
