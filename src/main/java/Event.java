public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (from: %s to: %s)", type.getSymbol(), status.getSymbol(), description, from, to);
    }
}