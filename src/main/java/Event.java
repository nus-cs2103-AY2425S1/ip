public class Event extends Task {
    private String eventStart;
    private String eventEnd;

    Event(String eventDesc, String eventStart, String eventEnd) {
        super(eventDesc);
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + eventStart + "to:" + eventEnd + ")";
    }
}
