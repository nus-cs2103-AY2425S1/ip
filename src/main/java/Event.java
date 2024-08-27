public class Event extends Task {
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String toFileString() {
        return String.format("E | %s | %s | %s | %s", super.getStatus() ? "1" : "0", super.getDescription(), this.from, this.to);
    }

    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.from, this.to);
    }
}
