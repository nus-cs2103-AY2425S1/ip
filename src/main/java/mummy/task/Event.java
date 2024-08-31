package mummy.task;


public class Event extends Task {
    private final String from;

    private final String to;

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
    public String toFileRecord() {
        return String.format("E | %d | %s | %s | %s", this.isDone() ? 1 : 0,
                this.getDescription(), this.getFrom(), this.getTo());
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
