public class Event extends Task {
    protected String eventStart;
    protected String eventEnd;
    private static final long serialVersionUID = 1L;

    public Event(String description, String eventStart, String eventEnd) {
        super(description);
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.eventStart + ")" + " (to: " + this.eventEnd + ")";
    }
}
