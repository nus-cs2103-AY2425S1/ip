public class Event extends Task {
    private String from;
    private String to;

    public Event (String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toStringDetails() {
        return String.format("[E]%s (from: %s to: %s)", super.toStringDetails(), this.from, this.to);
    }
}
