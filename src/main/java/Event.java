public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, boolean isDone, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        if (isDone) markAsDone();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toFileFormat() {
        return "E | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | " + from + " | " + to;
    }
}