public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String by, String to) {
        super(description);
        this.from = by;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + ", to: " + to + ")";
    }
}
