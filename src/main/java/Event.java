public class Event extends Task {
    private String eventStart;
    private String eventEnd;

    Event(String eventDesc, String eventStart, String eventEnd) {
        this(eventDesc, eventStart, eventEnd, false);
    }

    Event(String eventDesc, String eventStart, String eventEnd, boolean isDone) {
        super(eventDesc, isDone);
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + eventStart + "to:" + eventEnd + ")";
    }

    @Override
    public String getType() {
        return "Event";
    }

    @Override
    public String toTsv() {
        return getType() + "\t" + super.toTsv() + "\t" + eventStart + "\t" + eventEnd;
    }
}
