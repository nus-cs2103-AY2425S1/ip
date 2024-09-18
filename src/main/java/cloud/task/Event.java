package cloud.task;

import cloud.exception.CloudException;
import cloud.util.DateTime;

public class Event extends Task {
    protected DateTime start;
    protected DateTime end;

    /**
     * Constructs an Event object
     *
     * @param desc  description of the event
     * @param start start date and time of the event, in the format "dd/MM/yyyy HH:mm"
     * @param end   end date and time of the event, in the format "dd/MM/yyyy HH:mm"
     * @throws CloudException if the date or time input format is invalid
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
