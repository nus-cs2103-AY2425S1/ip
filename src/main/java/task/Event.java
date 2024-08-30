package task;

public class Event extends Task {
    private final String from, to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public Event setAsDone() {
        return new Event(this.getDescription(), true, this.getFrom(), this.getTo());
    }

    @Override
    public Event setAsUndone() {
        return new Event(this.getDescription(), false, this.getFrom(), this.getTo());
    }

    @Override
    public Event setDescription(String description) {
        return new Event(this.getDescription(), this.isDone(), this.getFrom(), this.getTo());
    }

    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.from, this.to);
    }

}
