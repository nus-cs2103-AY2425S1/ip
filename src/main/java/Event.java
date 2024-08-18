public class Event extends Task {
    /**
     * The start date/time of this Event.
     */
    protected String fromDateTime;
    /**
     * The end date/time of this event.
     */
    protected String toDateTime;

    /**
     * The constructor.
     *
     * @param description Event description.
     */
    public Event(String description, String fromDateTime, String toDateTime) throws BocchiException {
        super(description);
        if (fromDateTime == null || toDateTime == null) {
            throw new BocchiException("So..sorry, but you have specify both start and end time for an event.");
        }
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }

    /**
     * Get the start date/time of this event.
     * @return The start date/time of this event.
     */
    public String getToDateTime() {
        return toDateTime;
    }

    /**
     * Get the end date/time of this event.
     * @return The end date/time of this event.
     */
    public String getFromDateTime() {
        return fromDateTime;
    }

    /**
     * Returns a string representation of this event.
     *
     * @return A string representation of this event.
     */
    @Override
    public String toString() {
        return "[EVENT]" + super.toString() + " (from: " + fromDateTime + "; to: " + toDateTime + ")";
    }
}
