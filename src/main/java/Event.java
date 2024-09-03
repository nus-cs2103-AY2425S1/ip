public class Event extends Task{
    private final String from;
    private final String to;
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, boolean isComplete, String from, String to) {
        super(description, isComplete);
        this.from = from;
        this.to = to;
    }

    public String toSaveFormat() {
        return String.format("E | %s | %s | %s", super.toSaveFormat(), from ,to);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
    }
}
