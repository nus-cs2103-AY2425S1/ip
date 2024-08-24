public class Event extends Task {

    private String eventStart;
    private String eventEnd;
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
