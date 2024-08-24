package tasks;

public class Event extends Task {
    private final String start;
    private final String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.start, this.end);
    }

    @Override
    public String toFileString() {
        String startEnd = this.start + '-' + this.end;
        return String.format("E | %s | %s", super.toFileString(), startEnd);
    }
}
