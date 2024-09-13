package katheryne;

/**
 * Event Class is a child class of Task Class, and it has two additional
 * attribute from and to, which indicates the event's start time and end time.
 */

public class Event extends Task {
    public static final String TYPE = "Event";
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description, TYPE);
        this.from = from;
        this.to = to;
        assert this.from != null : "Time should be specified.";
        assert this.to != null : "Time should be specified.";
    }

    public String toString() {
        return String.format("[E]%s(from: %s to: %s)", super.toString(), this.from, this.to);
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String toSaveString() {
        if (isDone) {
            return String.format("E | %d | %s | %s-%s", 1, this.getDescription(), this.from, this.to);
        } else {
            return String.format("E | %d | %s | %s-%s", 0, this.getDescription(), this.from, this.to);
        }
    }

    private String getTo() {
        return to;
    }

    private String getFrom() {
        return from;
    }

    @Override
    public boolean equals(Task t) {
        if (!super.equals(t)) {
            return false;
        }
        Event event = (Event) t;
        boolean bool1 = this.getDescription().equals(event.getDescription());
        boolean bool2 = this.to.equals(event.getTo());
        boolean bool3 = this.from.equals(event.getFrom());
        return bool1 && bool2 && bool3;
    }

}