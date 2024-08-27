public class Event extends Task {
    protected String from;
    protected String to;
    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    public String saveString() {
        return String.format("E | %s | %s | %s | %s", description, isDone, from, to);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
    }
}
