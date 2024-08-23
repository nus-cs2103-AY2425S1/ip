public class Events extends Task {

    protected String eventStart;
    protected String eventEnd;

    public Events(String description, String eventStart, String eventEnd) {
        super(description);
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;

    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + eventStart + eventEnd + ")";
    }
}
