public class Event extends Task {
    private final String from;
    private final String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    String getSaveFormat() {
        return String.format("E | %d | %s | %s to %s", super.intComplete(), super.getDescription(), from, to);
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)", super.completedStringRepresentation(), super.getDescription(), from, to);
    }
}
