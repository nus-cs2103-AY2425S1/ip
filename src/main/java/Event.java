public class Event extends Task {
    private final String from;
    private final String to;

    Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    private Event(String description, String from, String to, Boolean hasCompleted) {
        super(description, hasCompleted);
        this.from = from;
        this.to = to;
    }

    @Override
    public Event markAsDone() {
        return new Event(getDescription(), this.from, this.to, true);
    }

    @Override
    public Event markAsNotDone() {
        return new Event(getDescription(), this.from, this.to, false);
    }

    public String getType() {
        return "[E]";
    }

    @Override
    public String toString() {
        return getType() + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
