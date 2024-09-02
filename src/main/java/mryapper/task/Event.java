package mryapper.task;

/**
 * An event task that occurs within the specified time.
 */
public class Event extends Task {
    private final String eventStart;
    private final String eventEnd;

    /**
     * Creates an event task.
     *
     * @param description The description of the event.
     * @param start The starting time or date of the event in string.
     * @param end The ending time or date of the event in string.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.eventStart = start;
        this.eventEnd = end;
    }

    @Override
    public String getDataString() {
        return String.format("E ||| %d ||| %s ||| %s ||| %s\n",
                this.getStatus(), this.getDescription(), this.eventStart, this.eventEnd);
    }
    @Override
    public String toString() {
        String detailsString = String.format(" (from: %s | to: %s)", eventStart, eventEnd);
        return "[E]" + super.toString() + detailsString;
    }
}
