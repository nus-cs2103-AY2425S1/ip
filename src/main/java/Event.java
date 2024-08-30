public class Event extends Task {
    private static final String TYPE = "E";
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[" + TYPE + "]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toStorage() {
        return TYPE + "|" + super.toStorage() + "|" + from + "|" + to;
    }
}
