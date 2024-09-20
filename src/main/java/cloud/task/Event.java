package cloud.task;

import cloud.util.DateTime;

/**
 * Represents an event with a start and end time.
 * An <code>Event</code> object includes a description, start time, and end time.
 */
public class Event extends Task {
    protected DateTime start;
    protected DateTime end;

    /**
     * Constructs an Event object
     *
     * @param desc  description of the event
     * @param start start date and time of the event, in the format "dd/MM/yyyy HH:mm"
     * @param end   end date and time of the event, in the format "dd/MM/yyyy HH:mm"
     */
    public Event(String desc, DateTime start, DateTime end) {
        super(desc);
        this.start = start;
        this.end = end;
    }

    @Override
    public String formatSave() {
        return "E | "
                + super.formatSave()
                + " | " + this.start.formatSave()
                + " | " + this.end.formatSave();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + String.format(" (from: %s to: %s)", this.start, this.end);
    }
}
