public class Event extends Task {
    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)", super.getStatusIcons(), super.description, this.start, this.end);
    }

    @Override
    public String toFileString() {
        return String.format("E | %d | %s | %s-%s", this.done ? 1 : 0, this.description, this.start, this.end);
    }
}
