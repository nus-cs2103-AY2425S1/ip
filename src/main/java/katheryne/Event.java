package katheryne;

/**
 * Event Class is a child class of Task Class, and it has two additional
 * attribute from and to, which indicates the event's start time and end time.
 */

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        assert this.from != null : "Time should be specified.";
        assert this.to != null : "Time should be specified.";
    }

    public String toString() {
        return String.format("[E]%s(from: %s to: %s)", super.toString(), this.from, this.to);
    }

    @Override
    public String toSaveString() {
        if (isDone) {
            return String.format("E | %d | %s | %s-%s", 1, this.getDescription(), this.from, this.to);
        } else {
            return String.format("E | %d | %s | %s-%s", 0, this.getDescription(), this.from, this.to);
        }
    }
}