package evan.task;

public class Event extends Task {
    protected final DateTime from;
    protected final DateTime to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = DateTime.parseInput(from);
        this.to = DateTime.parseInput(to);
    }

    @Override
    public String encodeAsString() {
        return String.format("E | %s | %s | %s | %s", (this.isDone ? "1" : "0"), description, from, to);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
    }
}