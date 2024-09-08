public class Event extends Task {
    protected String to;
    protected String from;

    public Event(String description, String to, String from) {
        super(description);
        this.to = to;
        this.from = from;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}