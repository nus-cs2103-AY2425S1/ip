public class Event extends Task {
    private String from;
    private String to;

    public Event(String name, boolean isDone, String to, String from) {
        super(name, isDone);
        this.to = to;
        this.from = from;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + super.toFileString() + " | " + from + " | " + to;
    }
}
