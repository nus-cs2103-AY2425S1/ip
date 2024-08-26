public class Event extends Task {
    private String from;
    private String to;

    public Event (String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event (String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toStringDetails() {
        return String.format("[E]%s (from: %s to: %s)", super.toStringDetails(), this.from, this.to);
    }

    @Override
    public String toFileFormat() {
        return String.format("%s | %d | %s | %s | %s", "E", getStatusBinary(), getDescription(), this.from, this.to);
    }
}
