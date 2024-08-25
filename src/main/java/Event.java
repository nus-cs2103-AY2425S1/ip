public class Event extends Task {

    private String eventStart;
    private String eventEnd;

    /**
     * Constructs a new 'Event' object which can be added to Tayoo's list. Event is defined as a task
     * with a start and end date/time
     *
     * @param title title of the event
     * @param start start date/time of the event
     * @param end end date/time of the event
     */
    public Event(String title, String start, String end) {
        super(title);
        this.eventStart = start;
        this.eventEnd = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
               " (from: " + eventStart + " to: " + eventEnd + " )";
    }
}
