public class Event extends Task {
    private String from;
    private String to;

    public Event(String name, String to, String from) {
        super(name);
        this.to = to;
        this.from = from;
    }

    public String toString() {
        return "[E] " + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
