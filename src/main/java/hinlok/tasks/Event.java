package hinlok.tasks;

public class Event extends Task {
    private String from;
    private String to;

    public Event(String name, String from, String to, boolean isDone) {
        super(name, isDone);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }

    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}